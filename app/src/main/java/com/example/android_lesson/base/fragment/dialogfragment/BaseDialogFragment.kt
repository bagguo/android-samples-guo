package com.example.android_lesson.base.fragment.dialogfragment

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android_lesson.R

open class BaseDialogFragment : DialogFragment() {
    companion object {
        val TAG: String = this.javaClass.simpleName

        /**
         * 防止crash并允许状态丢失
         */
        internal fun show(
            manager: FragmentManager,
            tag: String?,
            f: Fragment,
        ) {
            val ft = manager.beginTransaction()
            ft.add(f, tag)
            ft.commitAllowingStateLoss()
        }
    }

    override fun show(
        manager: FragmentManager,
        tag: String?,
    ) {
        Log.i(TAG, "show: ${this.javaClass.simpleName}")
        show(manager, tag, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ${this.javaClass.simpleName}")
        setStyle(
            STYLE_NORMAL,
            R.style.Theme_client_MimoDialog,
        )
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ${this.javaClass.simpleName}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ${this.javaClass.simpleName}")
        dialog?.window?.apply {
            // 背景透明
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.i(TAG, "onDismiss: ${this.javaClass.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ${this.javaClass.simpleName}")
    }
}
