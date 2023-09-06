package com.example.android_lesson.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android_lesson.R;

public class AnimationActivity extends AppCompatActivity {

    private Animation mLargeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mLargeAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        ImageView iv = findViewById(R.id.large_icon);
        iv.setAnimation(mLargeAnimation);

        iv.setOnClickListener((View view) -> {
            if (iv.getDrawable() != null) {
                iv.startAnimation(mLargeAnimation);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {//activity不可见
        super.onResume();
    }
}
