#include <string>
#include "sub.h"

extern "C" jstring JNICALL Java_com_step2hell_jni_SubActivity_hello(
        JNIEnv *env,
        jobject /*thiz*/) {
    std::string hello = "Hello from JNI";
    return env->NewStringUTF(hello.c_str());
}

extern "C" jfloat JNICALL Java_com_step2hell_jni_SubActivity_getPriceOfBook
        (JNIEnv *env, jobject, jobject bookObj) {
    jclass bookClass = env->FindClass("com/step2hell/jni/Book");
    jmethodID setPriceMethodID = env->GetMethodID(bookClass, "setPrice", "(F)V");
    env->CallVoidMethod(bookObj, setPriceMethodID, 23.0);   // 这里必须是float型，如果是整型则会赋值失败，最后结果为0.0
    jmethodID getPriceMethodID = env->GetMethodID(bookClass, "getPrice", "()F");
    jfloat price = env->CallFloatMethod(bookObj, getPriceMethodID);
    return price;
}