package com.example.guo.startup

import android.os.SystemClock
import android.util.Log

/**
 * Tiny debug-only timer. Filter Logcat with tag [TAG] (`StartupDemo`).
 */
object StartupTiming {
    const val TAG = "StartupDemo"

    inline fun <T> measure(label: String, block: () -> T): T {
        val start = SystemClock.elapsedRealtime()
        Log.i(TAG, "[$label] START")
        return try {
            block()
        } finally {
            val costMs = SystemClock.elapsedRealtime() - start
            Log.i(TAG, "[$label] END cost=${costMs}ms")
        }
    }

    fun log(message: String) {
        Log.i(TAG, message)
    }
}
