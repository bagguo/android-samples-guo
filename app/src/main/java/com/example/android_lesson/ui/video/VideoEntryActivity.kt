package com.example.android_lesson.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivityVideoEntryBinding

class VideoEntryActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "VideoEntryActivity"

        fun launch(context: Context) {
            context.startActivity(Intent(context, VideoEntryActivity::class.java))
        }
    }

    private val binding by lazy { ActivityVideoEntryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.initListener()

    }

    private fun ActivityVideoEntryBinding.initListener() {
        btnMediaPlayer.setOnClickListener { MediaPlayerSampleActivity.start(this@VideoEntryActivity) }
        btnVideoOrientation.setOnClickListener { VideoOrientationActivity.launch(this@VideoEntryActivity) }
    }

}


