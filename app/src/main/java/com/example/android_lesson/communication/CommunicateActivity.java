package com.example.android_lesson.communication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.AActivity;

public class CommunicateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        findViewById(R.id.btn_event_bus).setOnClickListener(view -> {
            Intent intent = new Intent(CommunicateActivity.this, AActivity.class);
            startActivity(intent);
        });
    }
}