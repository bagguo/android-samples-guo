//
// Created by darren on 2025/4/3.
//

//#include "hello.h"

#include <jni.h>
#include <stdio.h>

JNIEXPORT jstring JNICALL
Java_com_example_ndk_MainActivity_helloFromJNI(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "Hello from NDK!");
}
