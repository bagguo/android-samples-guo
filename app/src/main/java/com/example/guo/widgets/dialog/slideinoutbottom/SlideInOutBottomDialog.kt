package com.example.guo.widgets.dialog.slideinoutbottom

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import com.example.guo.R

/**
 * dialog加入从底部进入/退出过渡动画
 */
class SlideInOutBottomDialog(
    context: Context,
) : AppCompatDialog(context, R.style.BottomSlideDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_my)
        setCanceledOnTouchOutside(true)

        // 设置 BottomSheet 类似的全宽
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        window?.setGravity(Gravity.BOTTOM)
    }
}
