#include <jni.h>
#include <string>
#include "mystack.h"

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_apkfuns_ndksample_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    STACK stack = init_stack();
    return env->NewStringUTF(hello.c_str());
}
