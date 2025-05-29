package com.example.android_lesson.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.android_lesson.R
import com.example.android_lesson.base.activity.BaseFullScreenActivity


class FullscreenDynamicActivity : BaseFullScreenActivity() {
    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, FullscreenDynamicActivity::class.java),
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_dynamic)
    }
}
