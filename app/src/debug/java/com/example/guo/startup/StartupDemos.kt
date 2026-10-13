package com.example.guo.startup

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.io.File
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Separated startup anti-pattern cases with timing. Invoked only from [com.example.guo.DebugApp].
 */
object StartupDemos {

    private val firstActivityWorkDone = AtomicBoolean(false)

    fun runApplicationDemos(app: Application) {
        if (StartupDemoConfig.ENABLE_MAIN_THREAD_DISK_IO) {
            mainThreadDiskIo(app)
        } else {
            StartupTiming.log("SKIP MainThreadDiskIo")
        }

        if (StartupDemoConfig.ENABLE_BLOCKING_SDK_INIT) {
            blockingSdkInit()
        } else {
            StartupTiming.log("SKIP BlockingSdkInit")
        }

        if (StartupDemoConfig.ENABLE_PRE_FIRST_FRAME_WORK) {
            preFirstFrameWorkInApplication()
            registerFirstActivityPreFrameWork(app)
        } else {
            StartupTiming.log("SKIP PreFirstFrameWork")
        }
    }

    /** Case 1: main-thread disk I/O / heavy SharedPreferences sync. */
    private fun mainThreadDiskIo(context: Context) {
        StartupTiming.measure("MainThreadDiskIo") {
            val prefs = context.getSharedPreferences("startup_demo_prefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            repeat(StartupDemoConfig.DISK_IO_PREF_WRITES) { index ->
                editor.putString("k_$index", "v_$index".repeat(32))
            }
            // commit() is synchronous disk I/O on the calling thread (anti-pattern).
            editor.commit()

            val blob = ByteArray(StartupDemoConfig.DISK_IO_FILE_BYTES) { (it % 251).toByte() }
            File(context.filesDir, "startup_demo_blob.bin").writeBytes(blob)
        }
    }

    /** Case 2: blocking SDK / heavy init on the main thread. */
    private fun blockingSdkInit() {
        StartupTiming.measure("BlockingSdkInit") {
            // Stand-in for a sync analytics / push / map SDK .init(context).
            Thread.sleep(StartupDemoConfig.BLOCKING_SDK_SLEEP_MS)
            var checksum = 0
            repeat(50_000) { checksum = checksum xor (it * 31) }
            StartupTiming.log("BlockingSdkInit fake checksum=$checksum")
        }
    }

    /** Case 3a: unnecessary CPU work in Application before any Activity runs. */
    private fun preFirstFrameWorkInApplication() {
        StartupTiming.measure("PreFirstFrameWork.Application") {
            burnCpu("app")
        }
    }

    /** Case 3b: more work on the first Activity create path (still before first frame). */
    private fun registerFirstActivityPreFrameWork(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (!firstActivityWorkDone.compareAndSet(false, true)) return
                StartupTiming.measure("PreFirstFrameWork.FirstActivity(${activity.javaClass.simpleName})") {
                    burnCpu("activity")
                }
            }

            override fun onActivityStarted(activity: Activity) = Unit
            override fun onActivityResumed(activity: Activity) = Unit
            override fun onActivityPaused(activity: Activity) = Unit
            override fun onActivityStopped(activity: Activity) = Unit
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
            override fun onActivityDestroyed(activity: Activity) = Unit
        })
        StartupTiming.log("Registered FirstActivity pre-frame callback")
    }

    private fun burnCpu(seed: String) {
        val digest = MessageDigest.getInstance("SHA-256")
        var token = seed.toByteArray()
        repeat(StartupDemoConfig.PRE_FRAME_HASH_ROUNDS) {
            token = digest.digest(token)
        }
        StartupTiming.log("PreFirstFrameWork seed=$seed digest=${token.joinToString("") { "%02x".format(it) }.take(12)}…")
    }
}
