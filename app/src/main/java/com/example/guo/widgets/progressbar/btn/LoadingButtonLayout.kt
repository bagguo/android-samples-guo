package com.example.guo.widgets.progressbar.btn

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.example.guo.R

/**
 * 带文字和加载条的Button
 *
 * 本质上不是一个button
 */
class LoadingButtonLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
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
            proBar?.visibility = VISIBLE
        } else {
            proBar?.visibility = GONE
        }
    }
}