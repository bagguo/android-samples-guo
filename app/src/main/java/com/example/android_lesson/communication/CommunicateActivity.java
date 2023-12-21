package com.example.android_lesson.communication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.AActivity;
import com.example.android_lesson.service.start.ServiceTestActivity;

public class CommunicateActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, CommunicateActivity.class);
        context.startActivity(intent);
    }


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