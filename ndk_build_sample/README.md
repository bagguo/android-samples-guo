## 使用ndk-build构建so
安装ndk，在~/Library/Android/sdk/ndk/23.xx (对应版本)

```
cd app/src/main/jni
ndk-build
```

如果一切顺利，你会在 app/src/main/libs/arm64-v8a/ 目录下看到：
```
libhello.so
```