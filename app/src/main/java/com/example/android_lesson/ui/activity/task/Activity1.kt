package com.example.android_lesson.ui.activity.task

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R

/**
 * https://blog.csdn.net/u013008419/article/details/80020747
 *
 * TaskAffinity sample
 *
 * 操作：
 * 1 2在一个app
 * 3 4在另一个app
 * 启动Activity3, 再启动Activity4
 * 再启动Activity1，再启动Activity2
 *
 * 按返回键，回退顺序：
 * 2->4->3->1
 *
 * 原因：3 4 2在一个task内，taskId相同，退完该task后再到1的task
 */
class Activity1 : AppCompatActivity() {

    companion object {
        private const val TAG = "Activity1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(TAG, "onCreate: taskId: $taskId")

        findViewById<Button>(R.id.btn).setOnClickListener {
            Activity2.start(this@Activity1)
        }
    }
}