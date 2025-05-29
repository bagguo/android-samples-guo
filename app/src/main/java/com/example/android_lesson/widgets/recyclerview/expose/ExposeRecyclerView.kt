package com.example.android_lesson.widgets.recyclerview.expose

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 曝光埋点
 */
class ExposeRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(//自定义RecyclerView
    context, attrs, defStyle
) {

    interface ItemViewVisibleListener {//回调接口（显示、隐藏）
    fun onItemViewVisible(position: Int)
        fun onItemViewGone(position: Int)
    }

    // 回调监听器
    var itemViewVisibleListener: ItemViewVisibleListener? = null

    init {
        //注册滚动监听
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                handleScrolled()
            }
        })
    }

    //处理滚动回调，第一次加载数据也会触发
    private fun handleScrolled() {
        when (layoutManager) {
            is LinearLayoutManager -> {
//                handleVisibleItem(
//                    recyclerView,
//                    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
//                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition(),
//                    (layoutManager as LinearLayoutManager).itemCount
//                )
            }
        }
    }

    private fun handleVisibleItem(
        recyclerView: RecyclerView, firstVisiblePosition: Int, lastVisiblePosition: Int, itemCount: Int
    ) {
        for (i in 0 until itemCount) {//1
            when (i) {
                in firstVisiblePosition..lastVisiblePosition -> {
                    callListenerByItemViewRect(recyclerView.findViewHolderForAdapterPosition(i))
                }
                else -> {
                    itemViewVisibleListener?.onItemViewGone(i)
                }
            }
        }
    }

    //回调不同的接口
    private fun callListenerByItemViewRect(viewHolder: ViewHolder?) {
        viewHolder?.apply {
            if (itemView.isExpose()) {
                itemViewVisibleListener?.onItemViewVisible(adapterPosition)
            } else {
                itemViewVisibleListener?.onItemViewGone(adapterPosition)
            }
        }
    }

    //判断曝光面积是否满足条件
    fun View.isExpose(): Boolean {//2
        val rect = Rect()
        val isVisibleRect: Boolean = this.getGlobalVisibleRect(rect)
        return (isVisibleRect
                && rect.height() * rect.width() >= measuredHeight * measuredWidth * 2 / 3)
    }
}
