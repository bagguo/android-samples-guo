package com.example.android_lesson.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.R

class TransparentActivity : Activity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, TransparentActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_transparent)

    }
}