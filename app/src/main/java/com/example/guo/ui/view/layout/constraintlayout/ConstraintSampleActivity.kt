package com.example.guo.ui.view.layout.constraintlayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.guo.databinding.ActivityConstraintSampleBinding

class ConstraintSampleActivity : AppCompatActivity() {
    companion object {
        private val TAG = "ConstraintSampleActivity"

        fun start(context: Context) {
            val intent = Intent(context, ConstraintSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityConstraintSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.initView()
    }

    /**
     * Group
     */
    private fun ActivityConstraintSampleBinding.initView() {
        // 同时设置group和group referenced_ids元素，referenced_ids元素设置不生效
        group.isVisible = false
        // clCircle.isVisible = true // 不生效
        clCircle.post {
            clCircle.isVisible = true // 生效
        }

        btn.setOnClickListener {
            clCircle.isVisible = !clCircle.isVisible
        }
    }
}
