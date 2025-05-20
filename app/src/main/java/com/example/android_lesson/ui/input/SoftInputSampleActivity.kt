package com.example.android_lesson.ui.input

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivitySoftInputSampleBinding

class SoftInputSampleActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, SoftInputSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySoftInputSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoftInputSampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

//        getWindow().setDecorFitsSystemWindows(false)
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//        binding.flRoot.setOnApplyWindowInsetsListener()
    }
}