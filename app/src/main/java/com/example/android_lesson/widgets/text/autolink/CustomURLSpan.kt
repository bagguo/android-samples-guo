package com.example.android_lesson.widgets.text.autolink

import android.content.Context
import android.content.Intent
import android.text.TextPaint
import android.text.style.URLSpan
import android.util.Log
import android.view.View
import com.example.android_lesson.ui.webview.WebViewDemoActivity

class CustomURLSpan(private val url: String, private val context: Context) : URLSpan(url) {

    companion object {
        private const val TAG = "CustomURLSpan"
    }

    override fun onClick(widget: View) {
        Log.d(TAG, "onClick: =====CustomURLSpan自己处理link的点击事件")
        // 处理点击事件，跳转到自定义页面
        val intent = Intent(context, WebViewDemoActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = true // 设置链接下划线
    }
}
