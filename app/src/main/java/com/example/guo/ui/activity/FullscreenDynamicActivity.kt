package com.example.guo.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.guo.base.activity.BaseFullScreenActivity
import com.example.guo.databinding.ActivityFullscreenDynamicBinding

class FullscreenDynamicActivity : BaseFullScreenActivity() {
    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, FullscreenDynamicActivity::class.java),
            )
        }
    }

    private val binding by lazy { ActivityFullscreenDynamicBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
