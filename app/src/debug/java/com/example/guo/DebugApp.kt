package com.example.guo

import android.os.SystemClock
import android.util.Log
import com.example.guo.startup.StartupDemoConfig
import com.example.guo.startup.StartupDemos
import com.example.guo.startup.StartupTiming

/**
 * Debug-only Application that layers intentional startup anti-patterns on top of [App].
 * Wired via [src/debug/AndroidManifest.xml]; release keeps [App].
 */
class DebugApp : App() {

    override fun onCreate() {
        val startedAt = SystemClock.elapsedRealtime()
        Log.i(StartupTiming.TAG, "=== DebugApp.onCreate BEGIN (pid=${android.os.Process.myPid()}) ===")

        StartupTiming.measure("super.App.onCreate") {
            super.onCreate()
        }

        StartupDemos.runApplicationDemos(this)

        val totalMs = SystemClock.elapsedRealtime() - startedAt
        Log.i(
            StartupTiming.TAG,
            "=== DebugApp.onCreate END total=${totalMs}ms " +
                "(flags: diskIo=${StartupDemoConfig.ENABLE_MAIN_THREAD_DISK_IO}, " +
                "sdk=${StartupDemoConfig.ENABLE_BLOCKING_SDK_INIT}, " +
                "preFrame=${StartupDemoConfig.ENABLE_PRE_FIRST_FRAME_WORK}, " +
                "provider=${StartupDemoConfig.ENABLE_EARLY_CONTENT_PROVIDER}) ==="
        )
    }
}
