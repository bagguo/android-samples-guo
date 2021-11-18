package com.example.android_lesson.communication.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BActivity extends AppCompatActivity {

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