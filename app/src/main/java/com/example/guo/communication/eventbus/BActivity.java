package com.example.guo.communication.eventbus;

import android.os.Bundle;

import com.example.guo.R;
import com.example.guo.communication.eventbus.event.MessageEvent;

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