package com.example.guo.ui.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R
import androidx.core.net.toUri

class VideoOrientation2Activity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnToggleOrientation: Button
    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_orientation_2)

        videoView = findViewById(R.id.videoView)
        btnToggleOrientation = findViewById(R.id.btnToggleOrientation)

        val videoUri = ("android.resource://" + packageName + "/" + R.raw.video_land).toUri()
        videoView.setVideoURI(videoUri)
        videoView.start()

        btnToggleOrientation.setOnClickListener {
            isLandscape = !isLandscape

            requestedOrientation = if (isLandscape) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED // 会闪屏

    }
}