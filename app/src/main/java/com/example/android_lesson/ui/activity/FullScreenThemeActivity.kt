package com.example.android_lesson.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.R

/**
 * 通过主题设置全屏Activity
 */
class FullScreenThemeActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, FullScreenThemeActivity::class.java),
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // must
        setContentView(R.layout.activity_full_screen)
    }
}
