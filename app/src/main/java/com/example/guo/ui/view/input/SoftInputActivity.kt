package com.example.guo.ui.view.input

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.guo.databinding.ActivitySoftInputScrollviewBinding

class SoftInputActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SoftInputActivity"

        fun launch(context: Context) {
            val intent = Intent(context, SoftInputActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        ActivitySoftInputScrollviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.i(TAG, "onCreate: ")
        binding.initView()
    }


    /**
     * 键盘本质上是一个dialog
     * 弹出隐藏键盘activity不会销毁重建
     */
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    private fun ActivitySoftInputScrollviewBinding.initView() {
        root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            root.getWindowVisibleDisplayFrame(rect)
            val screenHeight = root.rootView.height
            val keyboardHeight = screenHeight - rect.bottom

            /**
             * 弹出键盘时：
             * 1.不能将titleBar顶出屏幕使用adjustResize
             * 2.使用adjustResize的效果是将整个window置于键盘上方，此时底部[tvBottom]会显示在键盘上方，此处键盘弹起时隐藏[tvBottom]
             */
            if (keyboardHeight < 200) { //键盘高度低于200视为键盘已收起，200是个经验值, 可调整
                // 延迟100ms显示，键盘消失动画需要时间，否则键盘还没消失先展示出来会闪一下
                tvBottom.postDelayed({
                    tvBottom.isVisible = true
                }, 100)
            } else {
                tvBottom.isVisible = false //隐藏不需要延迟
            }
        }
    }
}


