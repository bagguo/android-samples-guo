package com.example.android_lesson;

import android.app.Application;
import android.os.Debug;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //启动优化－开始抓取TraceView
//        Debug.startMethodTracing("trace_view_start");
    }
}
