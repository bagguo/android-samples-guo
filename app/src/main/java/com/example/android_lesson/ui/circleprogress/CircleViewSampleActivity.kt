package com.example.android_lesson.ui.circleprogress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_lesson.R

class CircleViewSampleActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, CircleViewSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_view_sample)
    }
}