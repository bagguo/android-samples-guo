package com.example.android_lesson.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.ipc.aidl.AIDLClientActivity;

public class IPCTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipca_test_ctivity);

        findViewById(R.id.btn_aidl).setOnClickListener(vew -> {
            Intent intent = new Intent(IPCTestActivity.this, AIDLClientActivity.class);
            startActivity(intent);
        });
    }
}