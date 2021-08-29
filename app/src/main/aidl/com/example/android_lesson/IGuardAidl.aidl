// IGuardAidl.aidl
package com.example.android_lesson;

// Declare any non-default types here with import statements

interface IGuardAidl {
    //唤醒服务
    void wakeUp(String title, String description, int iconRes);
}