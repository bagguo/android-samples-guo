package com.example.android_lesson.ext

import java.net.URL

/**
 * @title
 * @author Darren.eth
 * @Date
 */

/**
 * @title
 * @author Darren.eth
 * @Date
 */
fun main() {
    val url = "https://www.example.com/path/to/resource"
    val domain = url.getDomainFromUrl()
    println(domain)  // 输出: www.example.com
}

fun String.getDomainFromUrl(): String? {
    return try {
        val url = URL(this)
        url.host // 获取域名
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}