package com.example.guo.ui.textview.spannable

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R
import com.example.guo.databinding.ActivitySpannableSampleBinding
import com.example.guo.widgets.text.spannable.TextSeeMoreClick


class SpannableSampleActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, SpannableSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivitySpannableSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_sample)
        mBinding = ActivitySpannableSampleBinding.inflate(layoutInflater)

        val tvAnswer = findViewById<TextView>(R.id.tv_answer)
        var answer = "web3 is decent web3 is decent web3 is decentweb3 is decentweb3 is decent "
        if (answer.length > 40) {
            answer = answer.substring(0, 35)
        }
        val and = " and "
        val seeMore = "see more"


        val text = answer + and + seeMore
        val sb = SpannableStringBuilder(text)
        sb.setSpan(
            TextSeeMoreClick(),
            text.length - seeMore.length,
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

//        val tv1 = findViewById<TextView>(R.id.tv_style)
        //设置点击事件，加上这句话才有效果
        tvAnswer.movementMethod = LinkMovementMethod.getInstance()
        tvAnswer.highlightColor = resources.getColor(R.color.transparent)
        tvAnswer.text = sb


//        val sizeLength = "f...等X人";
//        val count = TextDrawable(sizeLength, SizeUtils.sp2px(16), context.getResources().getColor(R.color.title), Color.TRANSPARENT, 0, 0);
//        textView.setCompoundDrawables(null, null, count, null);
//        textView.setText(text)

    }

    /**
     * 判断是否有多行 文字处理
     */
//    private fun setLastIndexForLimit(): Boolean {
//        //实例化StaticLayout 传入相应参数
//        val staticLayout = StaticLayout(
//            getText(), getPaint(),
//            getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
//            Layout.Alignment.ALIGN_NORMAL, 1, 0, false
//        )
//        return staticLayout.lineCount > 1
//    }

}