#include "jni.h"
#include <string>
#include <jni.h>

extern "C" __attribute__((unused)) __attribute__((unused)) JNIEXPORT jstring JNICALL

Java_com_example_android_1lesson_jni_JNISampleActivity_stringFromJni(JNIEnv *env, jobject clazz) {
        std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}