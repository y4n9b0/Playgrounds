#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL Java_com_step2hell_jni_MainActivity_stringFromJNI__(
        JNIEnv *env,
        jobject /*thiz*/) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL Java_com_step2hell_jni_MainActivity_stringFromJNI__Ljava_lang_String_2(
        JNIEnv *env,
        jobject /*thiz*/,
        jstring jstr) {
    return jstr;
}