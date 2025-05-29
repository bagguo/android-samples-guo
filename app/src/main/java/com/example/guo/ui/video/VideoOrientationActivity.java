package com.example.guo.ui.video;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guo.R;
import com.example.guo.ext.AssetExtKt;

public class VideoOrientationActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(
                new Intent(context, VideoOrientationActivity.class)
        );
    }

    private static final String TAG = "VideoViewSampleActivity";

    /**
     * 使用方法1, 还是方法2
     */
    int method = 2;

    /**
     * 视频播放进度
     */
    int process = 0;

    Thread mThread = new Thread(() -> {
        while (!isDestroyed()) {
            try {
                VideoView videoView = findViewById(R.id.video_view);
                process = videoView.getCurrentPosition();
                Log.e(TAG, "call: run([])-> " + process);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    private OrientationEventListener mOrientationEventListener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrientationEventListener.disable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "call: onCreate([savedInstanceState])-> ");

        if (method == 1) {
            initView();
            initVideoView();

            mThread.start();
        } else if (method == 2) {
            setContentView(R.layout.activity_video_orientation2);
            initView2();
            initVideoView();
        }

        startOrientationEventListener();
    }

    private void initVideoView() {
        VideoView videoView = findViewById(R.id.video_view);
//        videoView.setVideoPath("/sdcard/123_4.mp4");
        videoView.setVideoURI(AssetExtKt.asset2Uri("video-land.mp4"));
        videoView.seekTo(process);
        videoView.start();
    }

    private void initView() {
        setContentView(R.layout.activity_video_orientation);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.button_land).setOnClickListener(v ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            );

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void initView2() {
        int orientation = getResources().getConfiguration().orientation;
        TextView checkBtn = findViewById(R.id.button_land);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            checkBtn.setText("切换为横屏===");
            ((TextView) findViewById(R.id.text_view)).setText("竖屏");

            checkBtn.setOnClickListener(v ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            );

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            checkBtn.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.text_view)).setText("横屏");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "call: onConfigurationChanged([newConfig])-> ");

        if (method == 1) {
            initView();
            initVideoView();
        } else if (method == 2) {
            initView2();
        }
    }

    @Override
    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    /**
     * 监听手机方向
     */
    private void startOrientationEventListener() {
        mOrientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.e(TAG, "call: onOrientationChanged([orientation])-> 方向:" + orientation);
                if (30 < orientation && orientation < 50) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                }
            }
        };
        mOrientationEventListener.enable();
    }
}