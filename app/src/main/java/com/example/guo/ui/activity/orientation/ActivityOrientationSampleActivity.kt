package com.example.guo.ui.activity.orientation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityOrientationSampleBinding

class ActivityOrientationSampleActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOrientationSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}