package com.example.guo.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.guo.R;

public class AnimationActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, AnimationActivity.class);
        context.startActivity(intent);
    }

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
