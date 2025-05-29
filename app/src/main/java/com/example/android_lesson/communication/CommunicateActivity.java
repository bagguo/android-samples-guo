package com.example.android_lesson.communication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.eventbus.AActivity;

public class CommunicateActivity extends AppCompatActivity {

    public static final String TAG = "CommunicateActivity";

    public static void start(Context context) {
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

        findViewById(R.id.btn_jump_action).setOnClickListener(view -> {
            Log.d(TAG, "onCreate: ====click");
            Intent intent = new Intent();
            intent.setAction("com.example.action.ui.moudle"); //隐式跳转
//            intent.setClassName("com.example.ui", "com.example.ui.UIMainActivity");
//            intent.setComponent(new ComponentName("com.example.ui", "com.example.ui.UIMainActivity"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                Log.d(TAG, "onCreate: ========resolve activity");
                startActivity(intent);
                finish();
            }
        });


    }
}