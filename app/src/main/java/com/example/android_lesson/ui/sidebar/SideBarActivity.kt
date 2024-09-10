package com.example.android_lesson.ui.sidebar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivitySideBarBinding
import com.example.android_lesson.ui.sidebar.DLSideBar.OnTouchingLetterChangedListener

class SideBarActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SideBarActivity"

        fun start(context: Context) {
            val intent = Intent(context, SideBarActivity::class.java)
            context.startActivity(intent)
        }
    }

    val binding by lazy {
        ActivitySideBarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.sideBar.setOnTouchingLetterChangedListener(mSBTouchListener)

    }

    /**
     * 右侧index选项监听
     */
    private val mSBTouchListener = OnTouchingLetterChangedListener {
        // 返回的是字母A～Z
        // TODO: 2019/4/1 这里更新你的列表

        Log.d(TAG, ": $this")
    }
}