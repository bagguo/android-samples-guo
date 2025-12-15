package com.example.guo.ui.view.topbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityCollapsingPinedSampleBinding

/**
 * CollapsingToolbar实现吸顶效果
 */
class CollapsingPinedSampleActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, CollapsingPinedSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        ActivityCollapsingPinedSampleBinding.inflate(
            layoutInflater,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}