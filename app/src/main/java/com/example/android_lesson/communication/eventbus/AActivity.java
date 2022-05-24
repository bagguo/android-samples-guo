package com.example.android_lesson.communication.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.event.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AActivity extends EventBusBaseActivity {

    private static final String TAG = AActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo);
        initEventBus();

        findViewById(R.id.btn_go_b_activity).setOnClickListener(view -> {
            Intent intent = new Intent(AActivity.this, BActivity.class);
            startActivity(intent);
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        Log.e(TAG, "onMessageEvent: =========收到event消息：" + event.text );
    }
}