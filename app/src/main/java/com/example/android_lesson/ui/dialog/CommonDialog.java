package com.example.android_lesson.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_lesson.R;

@SuppressWarnings("unused")
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }


    public static class Builder {

        private final Context context;

        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;

        private OnClickListener positiveBtnClickListener;
        private OnClickListener negativeBtnClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }


        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }


        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public Builder setContentView(View v) {
            return this;
        }


        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveBtnClickListener = listener;
            return this;
        }


        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveBtnClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeBtnClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeBtnClickListener = listener;
            return this;
        }


        public CommonDialog create() {
            final CommonDialog dialog = new CommonDialog(context, R.style.CommonAlertDialog);
            View layout = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
            ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.addContentView(layout, params1);


            TextView titleTv = layout.findViewById(R.id.dialog_title);
            titleTv.setText(title);
            TextView messageTv = layout.findViewById(R.id.dialog_message);
            messageTv.setText(message);

            TextView confirmBtn = layout.findViewById(R.id.btn_dialog_ok);
            confirmBtn.setText(positiveButtonText);
            TextView cancelBtn = layout.findViewById(R.id.btn_dialog_cancel);
            cancelBtn.setText(negativeButtonText);


            if (TextUtils.isEmpty(title)) {
                titleTv.setVisibility(View.GONE);
            }


            if (TextUtils.isEmpty(message)) {
                messageTv.setVisibility(View.GONE);
            }


            if (positiveBtnClickListener != null) {
                confirmBtn.setOnClickListener(v ->
                        positiveBtnClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE));
            }


            if (!TextUtils.isEmpty(negativeButtonText)) {
                if (negativeBtnClickListener != null) {
                    cancelBtn.setOnClickListener(v ->
                            negativeBtnClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE));
                }
            } else {
                cancelBtn.setVisibility(View.GONE);
            }

            return dialog;
        }

    }

}
