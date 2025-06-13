package com.example.guo.base.fragment.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guo.R

/**
 * https://juejin.cn/post/7296308638686789642
 */
open class BaseFullScreenDialogFragment : BaseDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).also {
            it.window?.let { window ->
                WindowCompat.setDecorFitsSystemWindows(window, false) // 设置全屏
                val controller = WindowCompat.getInsetsController(window, window.decorView)
                controller.hide(WindowInsetsCompat.Type.systemBars()) // 隐藏system bars
            }
        }

    override fun getTheme(): Int = R.style.FullScreenDialog
}
