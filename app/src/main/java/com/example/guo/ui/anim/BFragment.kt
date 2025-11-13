package com.example.guo.ui.anim

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guo.R
import kotlin.math.abs

class BFragment : Fragment() {
    private lateinit var gestureDetector: GestureDetector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_anim_b, container, false)

        gestureDetector =
            GestureDetector(
                requireContext(),
                object : GestureDetector.SimpleOnGestureListener() {
                    private val SWIPE_THRESHOLD = 100
                    private val SWIPE_VELOCITY_THRESHOLD = 100

                    override fun onDown(e: MotionEvent): Boolean = true

                    override fun onFling(
                        e1: MotionEvent?,
                        e2: MotionEvent,
                        velocityX: Float,
                        velocityY: Float,
                    ): Boolean {
                        if (e1 == null || e2 == null) return false

                        val diffX = e2.x - e1.x // pos2 - pos1 > 0 左 -> 右
                        val diffY = e2.y - e1.y

                        // 检测是否主要是水平滑动
                        if (abs(diffX) > abs(diffY) && abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 150) { // 左滑超过150px 生效
                                parentFragmentManager.popBackStack()
                                return true
                            }
                        }
                        return false
                    }
                },
            )

        // 捕获触摸事件
        view.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        return view
    }
}
