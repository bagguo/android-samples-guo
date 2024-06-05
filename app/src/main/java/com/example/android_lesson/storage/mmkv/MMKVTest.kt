package com.example.android_lesson.storage.mmkv

import android.util.Log
import com.example.android_lesson.util.MMKVUtils
import java.util.Random

open class MMKVTest {
    fun test() {

        try {
            Thread { // test mmkv
                for (i in 0..9999) {
                    val success = MMKVUtils.putString("key$i", "value$i")
                    Log.d("MMKV", "run: " + Thread.currentThread().name + " " + success)
                }
            }.start()

            Thread { // test mmkv
                for (i in 0..9999) {
                    val k = Random().nextInt() % 10000
                    Log.d(
                        "", "onCreate: " + Thread.currentThread().name + " " + k + " "
                                + MMKVUtils.getString("key$k")
                    )
                }
            }.start()

            Thread { // test mmkv
                for (i in 0..9999) {
                    val success = MMKVUtils.putString("key$i", "value$i")
                    Log.d("", "run: " + Thread.currentThread().name + " " + success)
                }
            }.start()


            Thread { // test mmkv
                for (i in 0..9999) {
                    val k = Random().nextInt() % 10000
                    Log.d(
                        "", "onCreate: " + Thread.currentThread().name + " " + k + " "
                                + MMKVUtils.getString("key$k")
                    )
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
