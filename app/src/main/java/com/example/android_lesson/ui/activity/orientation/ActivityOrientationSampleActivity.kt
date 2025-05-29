package com.example.android_lesson.ui.activity.orientation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityOrientationSampleBinding

class ActivityOrientationSampleActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOrientationSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}