package com.example.android_lesson.ui.textview.classifier

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.style.URLSpan
import android.view.textclassifier.TextClassifier
import android.view.textclassifier.TextLinks
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_lesson.R

class TextClassifierActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TextClassifierActivity::class.java)
            context.startActivity(intent)
        }
    }

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_classifier)

        val textView: TextView = findViewById(R.id.textView)
        val text = textView.text.toString()

        // TODO 点击无响应，该功能暂未调通
        // 创建 TextClassifier
        val textClassifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textView.textClassifier
        } else {
            TextClassifier.NO_OP
        }

        // 创建 TextLinks.Request
        val request = TextLinks.Request.Builder(text)
            .setEntityConfig(TextClassifier.EntityConfig.Builder()
                .setIncludedTypes(listOf(TextClassifier.TYPE_URL, TextClassifier.TYPE_EMAIL))
                .build())
            .build()

        // 使用 TextClassifier 生成链接
        val textLinks = textClassifier.generateLinks(request)

        // 创建 SpannableString
        val spannableString = SpannableString(text)

        // 应用链接到 SpannableString
        for (link in textLinks.links) {
            spannableString.setSpan(URLSpan(link.toString()), link.start, link.end, 0)
        }

        // 设置 SpannableString 到 TextView
        textView.text = spannableString
    }
}