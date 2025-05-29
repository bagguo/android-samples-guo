package com.example.guo.ui.textview.linehead

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.databinding.ActivityTextViewLineHeadBinding

/**
 * 先使用TextView 属性breakStrategy文本换行策略
 */
class TextViewLineHeadActivity : AppCompatActivity() {

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, TextViewLineHeadActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val binding by lazy { ActivityTextViewLineHeadBinding.inflate(layoutInflater) }

    private val text =
        "你即将访问第三方网站\nwww.baidu.com(下称“第三方网站”)，请确保该链接安全且可靠，避免造成资产损失。同时，你接受你对第三方网站的任何使用将受限于第三方网站的条款，并受其约束。你同意对于你使用第三方网站所导致的任何损失、损害及任何其他后果，Zapry及其关联方不承担任何责任。"
    private val textEnglish =
        "By using the third-party website\nwww.baidu.com(“TPW”), you accept that any use of the TPW willbe subject to and governed by theterms of the TPW. Unless expresslystated in writina.Zapry and its affiliates are not in any wayassociated with the owner oroperator of the TPW. You agree that Zapry are not responsible or liable forany loss, damage and any otherconsequences arising from your useof the TPW. Please be aware thatusing a TPW may result in a loss ordiminution of your assets."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            /**
             * BREAK_STRATEGY_SIMPLE：对应 xml 属性 "simple"
             * - BREAK_STRATEGY_HIGH_QUALITY：对应 xml 属性 "high_quality"
             * - BREAK_STRATEGY_BALANCED ：对应 xml 属性 "balanced"
             */
            tvSimple.text = text

            /**
             * 方案1, 该方案中文更好，
             * ⚠️但对英文单词会造成切割
             *
             * 如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
             */
            tv.post {
                // 设置文本不显示, 不能在主线程使用
                tv.setAdaptiveText(text)
                tvEnglish.setAdaptiveText(textEnglish)
            }

//                tv.getViewTreeObserver()
//                    .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//                        override fun onGlobalLayout() {
//                            tv.setAdaptiveText("")
//                            tv.getViewTreeObserver().removeOnGlobalLayoutListener(this)
//                        }
//                    })

            /**
             * 方案2：
             * 将textview中的字符全角化
             */
            tv2.text = "方案2:\n" + toDBC(text)
            tv2English.text = "方案2:\n" + toDBC(textEnglish)


        }

    }


    /**
     * 将textview中的字符全角化，即将所有的数字、字母及标点全部转为全角字符，使它们与汉字同占两个字节，这样就可以避免由于占位导致的排版混乱问题了。
     * https://www.cnblogs.com/runwind/p/4454657.html
     */
    fun toDBC(input: String): String {
        val c = input.toCharArray()
        for (i in c.indices) {
            if (c[i].code == 12288) {
                c[i] = 32.toChar()
                continue
            }
            if (c[i].code > 65280 && c[i].code < 65375) c[i] = (c[i].code - 65248).toChar()
        }
        return String(c)
    }
}