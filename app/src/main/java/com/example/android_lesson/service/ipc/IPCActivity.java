package com.example.android_lesson.service.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.service.ipc.aidl.ClientActivity;

public class IPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipcactivity);

        findViewById(R.id.btn_aidl).setOnClickListener(vew -> {
            Intent intent = new Intent(IPCActivity.this, ClientActivity.class);
            startActivity(intent);
        });
    }
}