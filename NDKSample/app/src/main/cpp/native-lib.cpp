#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_apkfuns_ndksample_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT void JNICALL Java_com_apkfuns_ndksample_MainActivity_startTicks
        (JNIEnv *env, jobject obj) {

}

/*
 * Class:     com_apkfuns_ndksample_MainActivity
 * Method:    StopTicks
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_apkfuns_ndksample_MainActivity_StopTicks
        (JNIEnv *env, jobject obj) {

}
