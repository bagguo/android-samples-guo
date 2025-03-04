package com.example.android_lesson.ui.toast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyberflow.mimolite.common.util.ToastUtils
import com.example.android_lesson.R
import com.example.android_lesson.databinding.ActivityToastSampleBinding

class ToastSampleActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ToastSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityToastSampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.apply {
            btn.setOnClickListener {
                Toast.makeText(this@ToastSampleActivity, "原生Toast", Toast.LENGTH_SHORT).show()
            }

            btnCenter.setOnClickListener {
                Toast.makeText(this@ToastSampleActivity, "这是一个居中的 Toast", Toast.LENGTH_SHORT)
                    .apply {
                        setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        show()
                    }
            }

            btnCenter2.setOnClickListener {
                ToastUtils.show(
                    this@ToastSampleActivity,
                    "这是一个居中的 Toast",
                )
            }

            btnIcon.setOnClickListener {
                ToastUtils.showIconToast(
                    this@ToastSampleActivity,
                    "Success",
                )
            }
        }

    }
}