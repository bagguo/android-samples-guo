package com.example.android_lesson.debug


import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.example.android_lesson.BuildConfig
import com.example.android_lesson.R
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern


/**
 * Description: DebugToolsManager
 * Date:  5/20/21 4:31 PM
 * @author StarkSong
 */
object DebugToolsManager {

    fun createDebugFloat(activity: Activity) {
        if (!BuildConfig.DEBUG) return

        EasyFloat.with(activity)
            // 设置浮窗xml布局文件
            .setLayout(R.layout.float_app)
            // 设置浮窗显示类型，默认只在当前Activity显示，可选一直显示、仅前台显示
            .setShowPattern(ShowPattern.FOREGROUND)
            // 设置吸附方式，共15种模式，详情参考SidePattern
            .setSidePattern(SidePattern.RESULT_HORIZONTAL)
            // 设置浮窗的标签，用于区分多个浮窗
            .setTag("testFloat")
            // 设置浮窗是否可拖拽
            .setDragEnable(true)
            // 设置浮窗的对齐方式和坐标偏移量
            .setGravity(Gravity.LEFT or Gravity.TOP, 0, 240)
            // 设置宽高是否充满父布局，直接在xml设置match_parent属性无效
            .setMatchParent(widthMatch = false, heightMatch = false)
            .setFilter()
            .registerCallback {
                createResult { _, _, view ->
                    view?.setOnClickListener {
                        Toast.makeText(activity, "click float", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }


}