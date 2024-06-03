package com.example.android_lesson

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

class App : Application() {

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this

        initARouter()
    }

    private fun initARouter() {
        //启动优化－开始抓取TraceView
        //        Debug.startMethodTracing("trace_view_start");

        //        if (isDebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        ARouter.openLog() // Print log
        ARouter.openDebug() // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)

        //        }
        ARouter.init(this) // As early as possible, it is recommended to initialize in the Application
    }
}