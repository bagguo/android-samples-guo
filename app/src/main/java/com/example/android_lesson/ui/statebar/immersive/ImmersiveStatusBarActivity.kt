package com.example.android_lesson.ui.statebar.immersive

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivityImmersiveStatusBarBinding

/**
 * 实现沉浸式状态栏:
 * 方式1：
 * CoordinatorLayout\ CollapsingToolbarLayout\ DrawerLayout布局设置fitsSystemWindows="true"
 * 设置window.statusBarColor = android.graphics.Color.TRANSPARENT
 *
 * 方式2:
 * 不使用上述布局的话设置
 * frameLayout.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
 *                 or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
 *
 *
 * https://blog.csdn.net/guolin_blog/article/details/123023395
 */

class ImmersiveStatusBarActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ImmersiveStatusBarActivity"

        fun start(context: Context) {
            val intent = Intent(context, ImmersiveStatusBarActivity::class.java)
            context.startActivity(intent)
        }
    }


    private val binding by lazy {
        ActivityImmersiveStatusBarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
    }
}