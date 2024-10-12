package com.example.android_lesson.webview.url

import com.example.android_lesson.ext.getDomainFromUrl
import java.net.URL

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


class UrlSamples {
    fun getDomainFromUrl(urlString: String): String? {
        return try {
            val url = URL(urlString)
            url.host // 获取域名
        } catch (e: Exception) {
            e.printStackTrace()
            urlString
        }
    }
}