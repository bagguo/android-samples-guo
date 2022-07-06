package com.example.android_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android_lesson.animation.AnimationActivity;
import com.example.android_lesson.communication.CommunicateActivity;
import com.example.android_lesson.dispatcheventdemo.DispatchEventDemoActivity;
import com.example.android_lesson.retrofit.RetrofitTest;
import com.example.android_lesson.rxjava.RxJavaTest;
import com.example.android_lesson.rxjavaretrofit.RxjavaRetrofitTest;
import com.example.android_lesson.service.start.ServiceTestActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_service).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ServiceTestActivity.class);
            startActivity(intent);
        });

        new RxJavaTest().test(this);
        new RetrofitTest().test();
        new RxjavaRetrofitTest().test();


        findViewById(R.id.main_animation_btn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.main_dispatch_event_btn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DispatchEventDemoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.main_ui_btn).setOnClickListener(view -> {
            Log.d(TAG, "onCreate: ====click");
            Intent intent = new Intent();
            intent.setAction("com.example.action.ui.moudle");
//            intent.setClassName("com.example.ui", "com.example.ui.UIMainActivity");
//            intent.setComponent(new ComponentName("com.example.ui", "com.example.ui.UIMainActivity"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                Log.d(TAG, "onCreate: ========resolve activity");
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btn_communication).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CommunicateActivity.class);
            startActivity(intent);
        });


    }
}