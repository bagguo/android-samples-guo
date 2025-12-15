package com.example.guo.ui.fragment.bottomsheet

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guo.R
import com.example.guo.databinding.FragmentStickyBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 底部button固定，上方有列表，可展开可收缩的fragment
 * https://github.com/dorianpavetic/StickyBottomSheet
 */
class StickyBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "StickyBottomSheetFragment"

        private const val BTN_BOTTOM_MARGIN = 40 //px

        private var collapsedMargin = 0
        private var buttonHeight = 0 //184 //dpi:144/50=2.88
        private var expandedHeight = 0 //1984

        fun newInstance(): StickyBottomSheetFragment {
            return StickyBottomSheetFragment()
        }
    }

    private lateinit var binding: FragmentStickyBottomSheetBinding
    private var buttonLp: ConstraintLayout.LayoutParams? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStickyBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StickBottomSheetDemoAdapter(
                initString()
            )
        binding.rv.setLayoutManager(
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        )
        binding.rv.setHasFixedSize(true)
        binding.rv.setAdapter(adapter)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface: DialogInterface ->
            Log.i(TAG, "onCreateDialog: ====")
            setupRatio(dialogInterface as BottomSheetDialog)
        }

        (dialog as BottomSheetDialog).behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0) { //Sliding happens from 0 (Collapsed) to 1 (Expanded) - if so, calculate margins
                    buttonLp!!.topMargin =
                        (((expandedHeight - buttonHeight) - collapsedMargin) * slideOffset + collapsedMargin).toInt()
                } else { //If not sliding above expanded, set initial margin
                    buttonLp!!.topMargin = collapsedMargin
                }
                binding.btn.setLayoutParams(buttonLp) //Set layout params to button (margin from top)
            }
        })

        return dialog
    }

    private fun setupRatio(dialog: BottomSheetDialog) {
        val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet) ?: return

        //Retrieve button parameters
        buttonLp = binding.btn.layoutParams as ConstraintLayout.LayoutParams

        //Retrieve bottom sheet parameters
        BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_COLLAPSED
        val lp = bottomSheet.layoutParams
        lp.height = dialogDefHeight //2205

        expandedHeight = lp.height
        //Peek height to 70% of expanded height (Change based on your view)
        val peekHeight = (expandedHeight / 1.3).toInt() //1696

        //Setup bottom sheet
        bottomSheet.layoutParams = lp
        BottomSheetBehavior.from(bottomSheet).skipCollapsed = false
        BottomSheetBehavior.from(bottomSheet).peekHeight = peekHeight
        BottomSheetBehavior.from(bottomSheet).isHideable = true

        //Calculate button margin from top
        //How tall is the button + experimental distance from bottom (Change based on your view)
        buttonHeight = binding.btn.height + BTN_BOTTOM_MARGIN //144+40  40=13.8dp
        collapsedMargin = peekHeight - buttonHeight //Button margin in bottom sheet collapsed state
        buttonLp!!.topMargin = collapsedMargin
        binding.btn.setLayoutParams(buttonLp)

        //OPTIONAL - Setting up margins
        val rvLp = binding.rv.layoutParams as ConstraintLayout.LayoutParams
        //60 is amount that you want to be hidden behind button
        val k = (buttonHeight - 40) / buttonHeight.toFloat()
        //Recyclerview bottom margin (from button)
        rvLp.bottomMargin = (k * buttonHeight).toInt()
        binding.rv.setLayoutParams(rvLp)
    }

    // 可以设置dialog最大高度，如: windowHeight * 90/ 100
    private val dialogDefHeight: Int //1984
        get() = windowHeight //* 90 / 100 //Calculates height for 90% of fullscreen

    private val windowHeight: Int //Calculates window height for fullscreen use 2205
        get() {
            val displayMetrics = DisplayMetrics()
            (requireContext() as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }

    private fun initString(): List<String> {
        val list: MutableList<String> = ArrayList()
        for (i in 0..34) list.add("Item $i")
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }
}