package com.example.android_lesson.dispatcheventdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android_lesson.R;
import com.example.android_lesson.dispatcheventdemo.customview.NewCardTestActivity;

public class DispatchEventDemoActivity extends AppCompatActivity {

    public static final String TAG = DispatchEventDemoActivity.class.getSimpleName();

    private LinearLayout rootLl;
    private LinearLayout linearLayout;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event_demo);

        rootLl = findViewById(R.id.root_ll);
        linearLayout = findViewById(R.id.linear_layout);
        view = findViewById(R.id.view);

        findViewById(R.id.dispatch_go_card_btn).setOnClickListener(view -> {
            startActivity(new Intent(DispatchEventDemoActivity.this, NewCardTestActivity.class));
        });


    }
}