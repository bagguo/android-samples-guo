package com.example.guo.ui.textview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.text.util.Linkify
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R
import com.example.guo.databinding.ActivityTextViewSampleBinding
import com.example.guo.widgets.text.autolink.CustomURLSpan
import com.example.guo.ui.textview.classifier.TextClassifierActivity
import com.example.guo.ui.textview.linehead.TextViewLineHeadActivity
import com.example.guo.ui.textview.select.TextSelectSampleActivity
import com.example.guo.ui.textview.select.im.MsgListTextSelectActivity
import com.example.guo.ui.webview.CustomWebViewActivity

class TextViewSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TextViewSampleActivity"

        fun start(context: Context) {
            val intent = Intent(context, TextViewSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityTextViewSampleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 自定义 TextView.autoLink 的url/phone/email/map点击事件
         */

        binding.apply {
            btnTextLineHead.setOnClickListener {
                TextViewLineHeadActivity.start(this@TextViewSampleActivity)
            }

            btnTextSelect.setOnClickListener {
                TextSelectSampleActivity.start(this@TextViewSampleActivity)
            }

            btnTextSelect2.setOnClickListener {
                MsgListTextSelectActivity.start(this@TextViewSampleActivity)
            }

            btnTextClassifier.setOnClickListener {
                TextClassifierActivity.start(this@TextViewSampleActivity)
            }

            /**
             * 方式1
             */
            //  该属性与link点击事件不冲突
            binding.textView.setTextIsSelectable(true)
            val text =
                "Visit https://www.example.com uniswap.org for more information. 18305917501 bagguo23@gmail.com"
            val spannableString = SpannableString(text)
            //只能添加一次Linkify
            Linkify.addLinks(spannableString, Linkify.WEB_URLS)
//        Linkify.addLinks(spannableString, Linkify.PHONE_NUMBERS)
//        Linkify.addLinks(spannableString, Linkify.EMAIL_ADDRESSES)
            val spans = spannableString.getSpans(0, spannableString.length, URLSpan::class.java)

            for (span in spans) {
                val start = spannableString.getSpanStart(span)
                val end = spannableString.getSpanEnd(span)
                val flags = spannableString.getSpanFlags(span)
                span.underlying
                spannableString.removeSpan(span)
                spannableString.setSpan(
                    CustomURLSpan(span.url, this@TextViewSampleActivity),
                    start,
                    end,
                    flags
                )
            }

            binding.textView.text = spannableString
            binding.textView.movementMethod = LinkMovementMethod.getInstance()


            /**
             * TextView选中弹出的菜单中加入自定义菜单，不能替换系统menu
             */
            textView.customSelectionActionModeCallback = object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    // 清空菜单
                    menu?.clear()
                    // 加载自定义菜单项
                    mode?.menuInflater?.inflate(R.menu.custom_selection_menu, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    when (item?.itemId) {
                        R.id.menu_copy -> {
                            val selectedText = textView.text.subSequence(
                                textView.selectionStart,
                                textView.selectionEnd
                            ).toString()
                            Toast.makeText(
                                this@TextViewSampleActivity,
                                selectedText,
                                Toast.LENGTH_SHORT
                            ).show()
                            // 处理复制逻辑，例如将文本复制到剪贴板
                            return true
                        }

                        R.id.menu_share -> {
                            val selectedText = textView.text.subSequence(
                                textView.selectionStart,
                                textView.selectionEnd
                            ).toString()
                            Toast.makeText(
                                this@TextViewSampleActivity,
                                selectedText,
                                Toast.LENGTH_SHORT
                            ).show()
                            // 处理分享逻辑，例如通过Intent分享文本
                            return true
                        }

                        else -> return false
                    }
                }

                override fun onDestroyActionMode(mode: ActionMode?) {}
            }
        }
        /**
         * 方式2
         */
//        binding.textView.text = " 10086 中国移动的 这是百度的 https://www.baidu.com/"
//        //与自定义autoLink点击冲突，设置该属性，点击link自己和系统(跳转系统浏览器)都会处理，无法只由自己处理
////        binding.textView.setTextIsSelectable(true)
//        binding.textView.autoLinkMask = Linkify.ALL
//        binding.textView.movementMethod =
//            LinkMovementMethodEx(
//                LinkClickListener {
//                    Toast.makeText(this, "系统处理link的点击事件", Toast.LENGTH_SHORT).show()
//                    Log.d(TAG, "onCreate: =====系统处理link的点击事件")
//
//                    //true  表示要自己处理  false 使用系统默认
//                    return@LinkClickListener true
//                })


        /**
         * 方式3
         */
        //识别url tel不好用
//        binding.textView.setTextIsSelectable(true)
//        val text = "Visit  https://www.example.com uniswap.org for more information. 18305917500 bagguo@163.com"
//        binding.textView.autoLinkMask = 0 // 禁用自动链接功能，因为我们会手动添加链接
//        binding.textView.text = addLinks(text)
//        binding.textView.movementMethod = CustomLinkMovementMethod()
    }

    private fun addLinks(text: String): Spannable {
        val spannableString = SpannableString(text)

        // 添加自定义的链接
        //识别url tel不好用
        val urlPattern = Regex("https?://\\S+")
        val emailPattern = Regex("\\S+@\\S+")
        val phonePattern = Regex("\\d{3}-\\d{3}-\\d{4}")

        setCustomClickableSpans(spannableString, urlPattern)
        setCustomClickableSpans(spannableString, emailPattern)
        setCustomClickableSpans(spannableString, phonePattern)

        return spannableString
    }

    private fun setCustomClickableSpans(spannableString: Spannable, pattern: Regex) {
        pattern.findAll(spannableString).forEach {
            val start = it.range.first
            val end = it.range.last + 1
            val link = it.value
            spannableString.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // 在这里处理点击事件
                    val intent = Intent(widget.context, CustomWebViewActivity::class.java)
                    intent.putExtra("link", link)
                    widget.context.startActivity(intent)
                }
            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}