package com.example.guo.ui.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R

/**
 * open ai生成
 */
class CustomWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_web_view)

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        val url = intent.getStringExtra("url")
        webView.loadUrl(url ?: "https://www.example.com")
    }
}