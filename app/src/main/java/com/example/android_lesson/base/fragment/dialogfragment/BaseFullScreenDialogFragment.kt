package com.example.android_lesson.base.fragment.dialogfragment

import android.view.Gravity

open class BaseFullScreenDialogFragment : BaseDialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setGravity(Gravity.FILL)
    }
}