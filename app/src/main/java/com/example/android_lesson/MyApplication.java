package com.example.android_lesson;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import com.example.android_lesson.service.ipc.LocalService;
import com.example.android_lesson.service.ipc.RemoteService;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//低版本
            //启动本地服务
            Intent localIntent = new Intent(this, LocalService.class);
            //启动守护进程
            Intent guardIntent = new Intent(this, RemoteService.class);
            startService(localIntent);
            startService(guardIntent);
//        }
    }
}
