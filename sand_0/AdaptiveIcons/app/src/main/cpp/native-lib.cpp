#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mxnavi_adaptiveicons_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


JNIEXPORT jstring JNICALL
Java_com_mxnavi_adaptiveicons_MainActivity_stringBtnFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello Kotlin";
    return env->NewStringUTF(hello.c_str());
}
