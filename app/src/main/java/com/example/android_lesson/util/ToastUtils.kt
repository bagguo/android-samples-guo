package com.cyberflow.mimolite.common.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.android_lesson.App
import com.example.android_lesson.R
import com.example.android_lesson.ui.textview.select.im.SelectUtils.Companion.dp2px
import java.lang.ref.WeakReference

object ToastUtils {

    private var lastToast: WeakReference<Toast>? = null

    /**
     * 文本toast
     */
    fun show(
        context: Context,
        @StringRes stringRes: Int,
        short: Boolean = true,
    ) {
        show(context, stringRes, short, null)
    }

    fun show(
        context: Context,
        text: String,
        short: Boolean = true,
    ) {
        show(context, text, short, null)
    }


    /**
     * 带图标的toast
     */
    fun showIconToast(
        context: Context,
        text: String,
        successIcon: Boolean = true,
        short: Boolean = true,
    ) {
        val iconRes = if (successIcon) {
            R.drawable.ic_toast_success_green_32
        } else {
            R.drawable.ic_toast_error_red_32
        }
        val icon = AppCompatResources.getDrawable(context, iconRes)

        show(context, text, short, icon)
    }

    fun showIconToast(
        context: Context,
        textRes: Int,
        iconResId: Int,
        short: Boolean = true,
    ) {
        show(context, textRes, short, iconResId)
    }

    fun showIconToast(
        context: Context,
        text: String,
        drawable: Drawable,
        short: Boolean = true,
    ) {
        show(context, text, short, drawable)
    }


    private fun show(
        context: Context,
        @StringRes stringRes: Int,
        short: Boolean = true,
        iconRes: Int? = null
    ) {
        if (context is Activity && (context.isFinishing || context.isDestroyed)) {
            return
        }

        val text = context.getString(stringRes)
        val icon = iconRes?.let { AppCompatResources.getDrawable(context, it) }

        show(context, text, short, icon)
    }

    private fun show(
        context: Context,
        text: String,
        short: Boolean = true,
        icon: Drawable? = null
    ) {
        if (context is Activity && (context.isFinishing || context.isDestroyed)) {
            return
        }

        val last = lastToast?.get()
        //
        // 这里为什么不复用last了，原因如下：
        //
        // 原生Toast直接set上一个会有显示时间不够的问题，连续点击会有一段时间无法展示
        // 所以别说复用上一个Toast对象是优化了
        //
        // 对于自定义Toast，如果页面跳转，前Toast持有的Activity是无效的
        //
        // 所以这里的用处就是立即隐藏上一个Toast
        //
        last?.cancel()

        val duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG


        // 自定义布局
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.custom_toast, null)

        val llRoot = view.findViewById<LinearLayout>(R.id.ll_root)
        val tv = view.findViewById<TextView>(R.id.tv)
        val iv = view.findViewById<ImageView>(R.id.iv)

        tv.text = text

        // icon
        if (icon != null) {
            iv.isVisible = true
            iv.setImageDrawable(icon)

            val p = dp2px(TOAST_PADDING_HORIZONTAL)
            llRoot.setPadding(p, p, p, p)
        }


        // toast
        val toast = Toast(App.mContext)
        toast.duration = duration

        // tip: 后台运行的应用将不会显示自定义提示视图 Android Build.VERSION_CODES.R
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()

        lastToast = WeakReference<Toast>(toast)
    }
}

private const val TOAST_PADDING_HORIZONTAL = 24f