package com.example.guo.base.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * 动态设置全屏
 * https://blog.csdn.net/yu540135101/article/details/82932177
 */
open class BaseFullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionStatusBar()
        hideBottomStatusBar()
        enableEdgeToEdge()
    }

    /**
     * 隐藏ActionBar和StatusBar
     */
    private fun hideActionStatusBar() {
        // set no title bar 需要在setContentView之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // no status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        // 特殊情况下
        supportActionBar?.hide()
        getActionBar()?.hide()
    }

    /**
     * 隐藏 NavigationBar和StatusBar
     */
    protected fun hideBottomStatusBar() {
        // 隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.getWindow().getDecorView()
            v.setSystemUiVisibility(View.GONE)
        } else if (Build.VERSION.SDK_INT >= 19) {
            // for new api versions.
            val decorView = window.decorView
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.setSystemUiVisibility(uiOptions)
        }
    }
}
