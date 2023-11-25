package com.example.android_lesson.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_lesson.databinding.ActivityUiEntryBinding
import com.example.android_lesson.ui.chapter_3.ViewActivity
import com.example.android_lesson.ui.circleprogress.CircleViewSampleActivity
import com.example.android_lesson.ui.constraintlayout.ConstraintLayoutSampleActivity
import com.example.android_lesson.ui.custom.CustomViewSimpleActivity

class UIEntryActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, UIEntryActivity::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var mBinding: ActivityUiEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUiEntryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnConstraintLayout.setOnClickListener { ConstraintLayoutSampleActivity.start(this) }
        mBinding.btnCircleView.setOnClickListener { CircleViewSampleActivity.start(this) }
        mBinding.btnCustomViewSimple.setOnClickListener { CustomViewSimpleActivity.start(this) }
        mBinding.btnView.setOnClickListener { ViewActivity.start(this) }

    }
}