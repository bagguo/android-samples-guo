package com.example.android_lesson.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_lesson.R;


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
    }


    public void showDialog(String message) {
        CommonDialog.Builder builder = new CommonDialog.Builder(this)
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