package com.example.android_lesson.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_lesson.R;

import androidx.appcompat.app.AppCompatActivity;

public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        findViewById(R.id.btn_custom_alert_dialog).setOnClickListener(view -> {
            showDialog("这是一个自定义弹窗");
        });

        findViewById(R.id.btn_show_custom_progress_dialog).setOnClickListener(view -> {
            CustomProgressDialog.showLoading(DialogDemoActivity.this);
        });

        findViewById(R.id.btn_stop_custom_progress_dialog).setOnClickListener(view -> {
            CustomProgressDialog.stopLoading();
        });
    }


    public void showDialog(String message) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.setTitle(message);
        builder.setMessage(message + "是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗是一个自定义弹窗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //唤醒沃掌控进行认证
                Toast.makeText(DialogDemoActivity.this, "ok click", Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogDemoActivity.this, "cancle click", Toast.LENGTH_LONG).show();

                dialog.dismiss();
                //直接关闭
//                finish();
            }
        });
        builder.create().show();
    }


}