# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ---------------------------- 马甲包混淆缩略字典 start------------------------------------------------
# 解决方案来自：https://github.com/WrBug/FrenziedProguard/tree/master

# 混淆的压缩比例，0-7, R8中不生效
-optimizationpasses 5
# 不要忽略 库（library）中非 public 的成员
-dontskipnonpubliclibraryclassmembers
# 指定混淆时采用的算法
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
# 指定外部模糊字典 proguard-abbreviation-beezy.txt 改为混淆文件名，下同
# 混淆结果不会是 a/b/c，而是字典里的词（如：ze, bzy, yz1）
-obfuscationdictionary proguardbuild/proguard-abbreviation-beezy.txt
# 指定class模糊字典
-classobfuscationdictionary proguardbuild/proguard-abbreviation-beezy.txt
# 指定package模糊字典
-packageobfuscationdictionary proguardbuild/proguard-abbreviation-beezy.txt
# ---------------------------- 马甲包混淆缩略字典 end------------------------------------------------
