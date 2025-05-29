package com.example.guo.ui.fragment.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.guo.R
import com.example.guo.base.fragment.dialogfragment.BaseFullScreenDialogFragment

class FullScreenSampleDialogFragment : BaseFullScreenDialogFragment() {
    companion object {
        const val TAG = "FullScreenSampleDialogFragment"

        fun newInstance(): FullScreenSampleDialogFragment = FullScreenSampleDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
        Log.i(TAG, "onCreate: ")
    }
}
