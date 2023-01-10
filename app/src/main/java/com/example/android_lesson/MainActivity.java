package com.example.android_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android_lesson.animation.AnimationActivity;
import com.example.android_lesson.communication.CommunicateActivity;
import com.example.android_lesson.dispatcheventdemo.DispatchEventDemoActivity;
import com.example.android_lesson.retrofit.RetrofitTest;
import com.example.android_lesson.rxjava.RxJavaTest;
import com.example.android_lesson.rxjavaretrofit.RxjavaRetrofitTest;
import com.example.android_lesson.ipc.IPCTestActivity;
import com.example.android_lesson.service.start.ServiceTestActivity;
import com.example.android_lesson.video.VideoDemoActivity;
import com.example.android_lesson.wallet.WalletActivity;
import com.example.android_lesson.webview.WebViewDemoActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        checkPermission();

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

        findViewById(R.id.main_btn_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VideoDemoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_communication).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CommunicateActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_service).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ServiceTestActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_ipc).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IPCTestActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_web_view).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebViewDemoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_wallet).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WalletActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //启动优化－抓取TraceView结束点
//        Debug.stopMethodTracing();
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}