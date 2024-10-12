package com.example.android_lesson.ui.loading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.example.android_lesson.R

/**
 * 带文字和加载条的Button
 *
 * 本质上不是一个button
 */
class LoadingButtonLayout @JvmOverloads constructor(
    context: Context,
    attrs: android.util.AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    val layout: RelativeLayout = LayoutInflater.from(context)
        .inflate(R.layout.layout_loading_button, this) as RelativeLayout
    private var proBar: ProgressBar? = layout.findViewById(R.id.proBar)


    fun setEnable(enable: Boolean) {
        isEnabled = enable
    }


    fun setLoading(loading: Boolean) {
        if (loading) {
            proBar?.visibility = View.VISIBLE
        } else {
            proBar?.visibility = View.GONE
        }
    }
}