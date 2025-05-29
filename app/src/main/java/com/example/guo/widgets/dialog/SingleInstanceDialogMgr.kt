package com.example.guo.widgets.dialog

import android.app.AlertDialog
import android.content.Context

object SingleInstanceDialogMgr {

    private var dialog: AlertDialog? = null

    @Volatile
    private var isShowing = false

    fun show(
        context: Context,
        title: String = "提示",
        message: String = "是否确认操作？",
        positiveText: String = "确认",
        negativeText: String = "取消",
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        if (isShowing){
            dismiss()
        }

        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveText) { _, _ ->
                onConfirm?.invoke()
            }
            .setNegativeButton(negativeText) { _, _ ->
                onCancel?.invoke()
            }

        dialog = builder.create()
        dialog?.setOnDismissListener { isShowing = false }
        dialog?.show()
        isShowing = true
    }

    fun dismiss() {
        dialog?.dismiss()
        dialog = null
        isShowing = false
    }
}