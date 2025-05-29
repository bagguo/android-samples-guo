package com.example.guo.ui.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.guo.R;
import com.example.guo.service.start.MusicService;

public class StartServiceTestActivity extends AppCompatActivity {
    public static void start(Context context){
        Intent intent = new Intent(context, StartServiceTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        findViewById(R.id.btn_start_service).setOnClickListener(view -> {
            Intent intent = new Intent(StartServiceTestActivity.this, MusicService.class);
            startService(intent);
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(view -> {
            Intent intent = new Intent(StartServiceTestActivity.this, MusicService.class);
            startService(intent);
        });

        findViewById(R.id.btn_bind_service).setOnClickListener(view -> {
            Intent intent = new Intent(StartServiceTestActivity.this, BIndServiceTestActivity.class);
            startActivity(intent);
        });
    }
}