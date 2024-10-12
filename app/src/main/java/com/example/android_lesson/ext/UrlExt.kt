package com.example.android_lesson.ext

import java.net.URL

/**
 * @title
 * @author Darren.eth
 * @Date
 */

fun String.getDomainFromUrl(): String? {
    return try {
        val url = URL(this)
        url.host // 获取域名
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}