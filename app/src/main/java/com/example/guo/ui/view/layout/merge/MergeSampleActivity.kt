package com.example.guo.ui.view.layout.merge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityMergeSampleBinding

class MergeSampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MergeSampleActivity"
    }

    private val binding by lazy { ActivityMergeSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
