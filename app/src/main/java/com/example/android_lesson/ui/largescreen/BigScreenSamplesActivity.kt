package com.example.android_lesson.ui.largescreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyberflow.mimolite.common.util.ToastUtils
import com.example.android_lesson.databinding.ActivityBigScreenSamplesBinding
import com.example.android_lesson.ui.largescreen.embedding.SplitListActivity
import com.example.android_lesson.ui.largescreen.responsiveviews.ResponsiveViewsSampleActivity

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

        btnJetpackWindowManager.setOnClickListener {
            ToastUtils.show(this@BigScreenSamplesActivity, "To implements")
        }

        btnActivityEmbedding.setOnClickListener {
            SplitListActivity.launch(this@BigScreenSamplesActivity)
        }
    }
}