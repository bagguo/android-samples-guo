package com.example.guo.ui.consecutivescroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityConsecutiveScrollerSampleBinding

class ConsecutiveScrollerSampleActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConsecutiveScrollerSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
