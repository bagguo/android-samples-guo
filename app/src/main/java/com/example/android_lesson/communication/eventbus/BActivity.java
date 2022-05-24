package com.example.android_lesson.communication.eventbus;

import android.os.Bundle;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class BActivity extends EventBusBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        findViewById(R.id.btn_post).setOnClickListener(view -> {

            MessageEvent event = new MessageEvent();
            event.text = "text from BActivity";
            EventBus.getDefault().post(event);
        });
    }
}