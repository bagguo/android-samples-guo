package com.example.android_lesson.video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.android_lesson.R;
import com.example.android_lesson.communication.CommunicateActivity;

public class VideoDemoActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, VideoDemoActivity.class);
        context.startActivity(intent);
    }

    private String mFilePath;
    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_demo);
        initButton();

        mMediaPlayer = new MediaPlayer();
        mSurfaceView = findViewById(R.id.surface_view);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(@NonNull SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (position > 0) {
                    play();
                    mMediaPlayer.seekTo(position);
                    position = 0;
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
    }

    private void initButton() {
        findViewById(R.id.btn_play).setOnClickListener(view -> {
            if (!mMediaPlayer.isPlaying()) {
                play();
            }
        });

        findViewById(R.id.btn_pause).setOnClickListener(view -> {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            } else {
                mMediaPlayer.start();//无效　未测试通
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(view ->
                mMediaPlayer.stop()
        );
    }

    private void play() {
        mFilePath = "/mnt/sdcard/phone.mp4";//getExternalFilesDir().getAbsolutePath();
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(mFilePath);
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer.isPlaying()) {
            position = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }
}