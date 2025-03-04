package com.example.android_lesson.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivitySamplesBinding

class ActivitySamplesActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ActivitySamplesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivitySamplesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTransparentActivity.setOnClickListener {
            TransparentActivity.launch(this)
        }

    }
}