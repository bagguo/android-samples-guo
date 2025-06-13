package com.example.guo.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guo.base.fragment.dialogfragment.BaseFullScreenDialogFragment
import com.example.guo.databinding.FragmentFullscreenSampleBinding

class FullScreenSampleDialogFragment : BaseFullScreenDialogFragment() {
    companion object {
        const val TAG = "FullScreenSampleDialogFragment"

        fun newInstance(): FullScreenSampleDialogFragment = FullScreenSampleDialogFragment()
    }

    private val binding by lazy { FragmentFullscreenSampleBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = binding.root // inflater.inflate(R.layout.fragment_fullscreen_sample, container, false)

    override fun onStart() {
        super.onStart()
        // 未调通
        // https://juejin.cn/post/7296308638686789642
//        val window = dialog?.window
//        window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT,
//        )
//        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//
//        val headerLayout: View = requireView().findViewById(R.id.fullscreen_header)
//
//        window?.let {
//            EdgeToEdgeUtils.applyEdgeToEdge(
//                it,
//                true,
//                ViewUtils.getBackgroundColor(headerLayout),
//                null,
//            )
//
//            WindowCompat.setDecorFitsSystemWindows(window, false) // 设置全屏
//            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // 隐藏system bars
//        }
    }
}
