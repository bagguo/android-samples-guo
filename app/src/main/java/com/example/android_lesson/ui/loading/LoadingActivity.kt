package com.example.android_lesson.ui.loading

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoadingActivity::class.java)
            context.startActivity(intent)
        }
    }

    val binding: ActivityLoadingBinding by lazy {
        ActivityLoadingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            loadingBtn.isEnabled = true
            loadingBtn.setOnClickListener {
                loadingBtn.isEnabled = false
                loadingBtn.setLoading(false)
            }
        }
    }
}