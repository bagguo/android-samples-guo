package com.example.android_lesson.widgets.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android_lesson.R

@Suppress("unused")
class CommonConfirmDialog : Dialog {
    constructor(context: Context) : super(context)

    constructor(context: Context, theme: Int) : super(context, theme)

    class Builder(private val context: Context) {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonText: String? = null
        private var negativeButtonText: String? = null

        private var positiveBtnClickListener: DialogInterface.OnClickListener? = null
        private var negativeBtnClickListener: DialogInterface.OnClickListener? = null

        fun setMessage(message: String?): Builder {
            this.message = message
            return this
        }

        fun setMessage(message: Int): Builder {
            this.message = context.getText(message) as String
            return this
        }

        fun setTitle(title: Int): Builder {
            this.title = context.getText(title) as String
            return this
        }

        fun setTitle(title: String?): Builder {
            this.title = title
            return this
        }

        fun setContentView(v: View?): Builder {
            return this
        }

        fun setPositiveButton(
            positiveButtonText: Int,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            this.positiveButtonText = context
                .getText(positiveButtonText) as String
            this.positiveBtnClickListener = listener
            return this
        }

        fun setPositiveButton(
            positiveButtonText: String?,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            this.positiveButtonText = positiveButtonText
            this.positiveBtnClickListener = listener
            return this
        }

        fun setNegativeButton(
            negativeButtonText: Int,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            this.negativeButtonText = context
                .getText(negativeButtonText) as String
            this.negativeBtnClickListener = listener
            return this
        }

        fun setNegativeButton(
            negativeButtonText: String?,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            this.negativeButtonText = negativeButtonText
            this.negativeBtnClickListener = listener
            return this
        }

        fun create(): CommonConfirmDialogJ {
            val dialog = CommonConfirmDialogJ(context, R.style.CommonAlertDialog)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_common, null)
            val params1 = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.addContentView(layout, params1)

            val titleTv = layout.findViewById<TextView>(R.id.dialog_title)
            titleTv.setText(title)
            val messageTv = layout.findViewById<TextView>(R.id.dialog_message)
            messageTv.setText(message)

            val confirmBtn = layout.findViewById<TextView>(R.id.btn_dialog_ok)
            confirmBtn.setText(positiveButtonText)
            val cancelBtn = layout.findViewById<TextView>(R.id.btn_dialog_cancel)
            cancelBtn.setText(negativeButtonText)

            if (TextUtils.isEmpty(title)) {
                titleTv.setVisibility(View.GONE)
            }

            if (TextUtils.isEmpty(message)) {
                messageTv.setVisibility(View.GONE)
            }

            if (positiveBtnClickListener != null) {
                confirmBtn.setOnClickListener(View.OnClickListener { v: View? ->
                    positiveBtnClickListener!!.onClick(
                        dialog,
                        BUTTON_POSITIVE
                    )
                })
            }

            if (!TextUtils.isEmpty(negativeButtonText)) {
                if (negativeBtnClickListener != null) {
                    cancelBtn.setOnClickListener(View.OnClickListener { v: View? ->
                        negativeBtnClickListener!!.onClick(
                            dialog,
                            BUTTON_NEGATIVE
                        )
                    })
                }
            } else {
                cancelBtn.setVisibility(View.GONE)
            }

            return dialog
        }
    }
}
