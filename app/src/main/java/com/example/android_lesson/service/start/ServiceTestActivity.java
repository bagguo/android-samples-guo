package com.example.android_lesson.service.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.ipc.IPCTestActivity;
import com.example.android_lesson.service.bind.BIndServiceTestActivity;

public class ServiceTestActivity extends AppCompatActivity {
    public static void start(Context context){
        Intent intent = new Intent(context, ServiceTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        findViewById(R.id.btn_start_service).setOnClickListener(view -> {
            Intent intent = new Intent(ServiceTestActivity.this, MusicService.class);
            startService(intent);
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(view -> {
            Intent intent = new Intent(ServiceTestActivity.this, MusicService.class);
            startService(intent);
        });

        findViewById(R.id.btn_bind_service).setOnClickListener(view -> {
            Intent intent = new Intent(ServiceTestActivity.this, BIndServiceTestActivity.class);
            startActivity(intent);
        });
    }
}