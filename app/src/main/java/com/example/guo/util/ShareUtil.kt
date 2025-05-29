package com.example.guo.util

import android.app.Activity
import android.content.Intent

/**
 * @title
 * @author Darren.eth
 */

fun String.callSystemShare(context: Activity) {
    if (this.isEmpty()) {
        return
    }

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, this@callSystemShare)  // 设置要分享的 URL
        type = "text/*"  // 设置分享内容类型
    }

    // 使用 chooser 选择分享应用
    val chooser = Intent.createChooser(shareIntent, "分享链接")
    context.startActivity(chooser)
}