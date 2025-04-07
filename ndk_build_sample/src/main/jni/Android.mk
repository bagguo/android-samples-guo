LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# 生成的 .so 文件名是 libhello.so
LOCAL_MODULE    := hello
# 指定 C 代码文件
LOCAL_SRC_FILES := hello.c

include $(BUILD_SHARED_LIBRARY)
