package com.example.android_lesson.ui.video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.android_lesson.R;

import java.io.IOException;

public class MediaPlayerSampleActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, MediaPlayerSampleActivity.class);
        context.startActivity(intent);
    }

    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_sample);
        initButton();

        mMediaPlayer = new MediaPlayer();
        SurfaceView mSurfaceView = findViewById(R.id.surface_view);
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

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

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
        // "/mnt/sdcard/phone.mp4"; //getExternalFilesDir().getAbsolutePath();
        AssetFileDescriptor mAfd;
        try {
            mAfd = getAssets().openFd("video-land.mp4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(mAfd);
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