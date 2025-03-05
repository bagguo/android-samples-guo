package com.example.android_lesson.ui.bigscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityBigScreenSamplesBinding
import com.example.android_lesson.ui.bigscreen.responsiveviews.ResponsiveViewsSampleActivity

class BigScreenSamplesActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, BigScreenSamplesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityBigScreenSamplesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.initView()
    }

    private fun ActivityBigScreenSamplesBinding.initView() {
        btnResponsiveViews.setOnClickListener {
            ResponsiveViewsSampleActivity.launch(this@BigScreenSamplesActivity)
        }

    }
}