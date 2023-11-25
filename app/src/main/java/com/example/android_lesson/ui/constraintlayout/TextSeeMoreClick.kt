package com.example.android_lesson.ui.constraintlayout

import android.graphics.Color
import android.text.TextPaint

import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.android_lesson.App


class TextSeeMoreClick : ClickableSpan() {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        //设置文本的颜色
        ds.color = Color.RED
        //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
        ds.isUnderlineText = false
    }

    override fun onClick(widget: View) {
        // 执行点击逻辑
        Log.w("TAG", "onClick: ====点击了TextAgreementClick")
        Toast.makeText(App.mContext, "onClick: ====点击了TextAgreementClick", Toast.LENGTH_SHORT).show()
    }


}
