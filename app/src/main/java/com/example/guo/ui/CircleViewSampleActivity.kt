package com.example.guo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R

class CircleViewSampleActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, CircleViewSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_view_sample)
    }
}