#include <jni.h>
#include <string>
#include <malloc.h>
#include <cstring>
#include "android/log.h"
#include "pthread.h"

#define TAG "SoChecker"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)


void ByteToHexStr2(const char *source, char *dest, int sourceLen) {
    char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                        'A', 'B', 'C', 'D', 'E', 'F'};
    int lowByte, highByte;
    for (int i = 0; i < sourceLen; i++) {
        highByte = source[i] >> 4 & 0x0f;
        lowByte = source[i] & 0x0f;
        // LOGD("i=%d, h=%d, l=%d", i, highByte, lowByte);
        dest[i * 2] = hexDigits[highByte];
        dest[i * 2 + 1] = hexDigits[lowByte];
    }
}

jstring ToMd5(JNIEnv *env, jbyteArray source) {
    // MessageDigest类
    jclass classMessageDigest = env->FindClass("java/security/MessageDigest");
    // MessageDigest.getInstance()静态方法
    jmethodID midGetInstance = env->GetStaticMethodID(classMessageDigest, "getInstance",
                                                      "(Ljava/lang/String;)Ljava/security/MessageDigest;");
    // MessageDigest object
    jobject objMessageDigest = env->CallStaticObjectMethod(classMessageDigest, midGetInstance,
                                                           env->NewStringUTF("md5"));

    // update方法，这个函数的返回值是void，写V
    jmethodID midUpdate = env->GetMethodID(classMessageDigest, "update", "([B)V");
    env->CallVoidMethod(objMessageDigest, midUpdate, source);

    // digest方法
    jmethodID midDigest = env->GetMethodID(classMessageDigest, "digest", "()[B");
    jbyteArray objArraySign = (jbyteArray) env->CallObjectMethod(objMessageDigest, midDigest);

    jsize intArrayLength = env->GetArrayLength(objArraySign);
    jbyte *byte_array_elements = env->GetByteArrayElements(objArraySign, NULL);
    size_t length = (size_t) intArrayLength * 2 + 1;
    char *char_result = (char *) malloc(length);
    memset(char_result, 0, length);

    // 将byte数组转换成16进制字符串，发现这里不用强转，jbyte和unsigned char应该字节数是一样的
    ByteToHexStr2((const char *) byte_array_elements, char_result, intArrayLength);
    // 在末尾补\0
    *(char_result + intArrayLength * 2) = '\0';

    jstring stringResult = env->NewStringUTF(char_result);
    // release
    env->ReleaseByteArrayElements(objArraySign, byte_array_elements, JNI_ABORT);
    // 释放指针使用free
    free(char_result);
    return stringResult;
}

jstring loadSignature(JNIEnv *env, jobject context) {
    // 获得Context类
    jclass cls = env->GetObjectClass(context);
    // 得到getPackageManager方法的ID
    jmethodID mid = env->GetMethodID(cls, "getPackageManager",
                                     "()Landroid/content/pm/PackageManager;");

    // 获得应用包的管理器
    jobject pm = env->CallObjectMethod(context, mid);

    // 得到getPackageName方法的ID
    mid = env->GetMethodID(cls, "getPackageName", "()Ljava/lang/String;");
    // 获得当前应用包名
    jstring packageName = (jstring) env->CallObjectMethod(context, mid);

    // 获得PackageManager类
    cls = env->GetObjectClass(pm);
    // 得到getPackageInfo方法的ID
    mid = env->GetMethodID(cls, "getPackageInfo",
                           "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    // 获得应用包的信息
    jobject packageInfo = env->CallObjectMethod(pm, mid, packageName, 0x40); //GET_SIGNATURES = 64;
    // 获得PackageInfo 类
    cls = env->GetObjectClass(packageInfo);
    // 获得签名数组属性的ID
    jfieldID fid = env->GetFieldID(cls, "signatures", "[Landroid/content/pm/Signature;");
    // 得到签名数组
    jobjectArray signatures = (jobjectArray) env->GetObjectField(packageInfo, fid);
    // 得到签名
    jobject signature = env->GetObjectArrayElement(signatures, 0);

    // 获得Signature类
    cls = env->GetObjectClass(signature);
    // 得到toCharsString方法的ID
    mid = env->GetMethodID(cls, "toByteArray", "()[B");
    // 返回当前应用签名信息
    jbyteArray signatureByteArray = (jbyteArray) env->CallObjectMethod(signature, mid);

    return ToMd5(env, signatureByteArray);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_apkfuns_socheckerdemo_MainActivity_getSignature(JNIEnv *env, jobject instance,
                                                         jobject context) {
    return loadSignature(env, context);
}


void checkSignature(JNIEnv *env) {
    // RELEASE_MODE这个宏是通过编译脚本设定的，如果是release模式，
    // 则RELEASE_MODE=1，否则为0或者未定义
#ifdef RELEASE_MODE
    if (RELEASE_MODE == 1) {
        jclass appClass = env->FindClass("com/apkfuns/socheckerdemo/App");
        jmethodID getContextMethod = env->GetStaticMethodID(appClass, "getContext",
                                                            "()Landroid/content/Context;");
        jobject context = env->CallStaticObjectMethod(appClass, getContextMethod);
        jstring sign = loadSignature(env, context);
        if (strcmp("DF3F2288139E1673EB4A2899E6570C36", env->GetStringUTFChars(sign, 0)) != 0) {
            // 抛出异常
            env->ExceptionClear();
            jclass newExcCls = env->FindClass("java/lang/IllegalArgumentException");
            env->ThrowNew(newExcCls, "SoChecker Signature Error");
            return;
        }
        LOGD("The app signature is correct.");
    } else {
        LOGD("debug mode");
    }
#endif
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGD("JNI_OnLoad");
    JNIEnv *env;
    if (vm->GetEnv((void **) (&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    checkSignature(env);
    LOGD("JNI_OnLoad finish");
    return JNI_VERSION_1_6;
}
