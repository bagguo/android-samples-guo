package com.example.android_lesson.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_lesson.R;
import com.example.android_lesson.widgets.dialog.CommonConfirmDialogJ;
import com.example.android_lesson.widgets.dialog.CustomProgressDialog;
import com.example.android_lesson.widgets.dialog.SingleInstanceDialogMgr;


import androidx.appcompat.app.AppCompatActivity;

public class DialogSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        findViewById(R.id.btn_common_dialog).setOnClickListener(view -> {
            showDialog("这是一个自定义弹窗");
        });

        findViewById(R.id.btn_show_custom_progress_dialog).setOnClickListener(view -> {
            CustomProgressDialog.showLoading(DialogSampleActivity.this);
        });

        findViewById(R.id.btn_stop_custom_progress_dialog).setOnClickListener(view -> {
            CustomProgressDialog.stopLoading();
        });

        findViewById(R.id.btn_single_instance_dialog).setOnClickListener(view -> {
            SingleInstanceDialogMgr.INSTANCE.show(
                    DialogSampleActivity.this,
                    "title",
                    "这是message",
                    "确认",
                    "Cancel",
                    null,
                    null);

            SingleInstanceDialogMgr.INSTANCE.show(
                    DialogSampleActivity.this,
                    "title 2",
                    "这是message2",
                    "确认2",
                    "Cancel2",
                    null,
                    null);
        });
    }


    public void showDialog(String message) {
        CommonConfirmDialogJ.Builder builder = new CommonConfirmDialogJ.Builder(this)
                .setTitle(message)
                .setMessage(message + "是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //唤醒沃掌控进行认证
                        Toast.makeText(DialogSampleActivity.this, "ok click", Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogSampleActivity.this, "cancle click", Toast.LENGTH_LONG).show();

                        dialog.dismiss();
                        //直接关闭
//                finish();
                    }
                });
        builder.create().show();
    }


}