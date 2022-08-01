package com.example.android_lesson.ipc.binder.bigbitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android_lesson.R;

public class ReceiverBigBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_big_bitmap);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("big_bitmap_bundle");
        if (bundle != null) {
            ImageBinder binder = (ImageBinder) bundle.getBinder("binder_bitmap");
            Drawable drawable = binder.getDrawable();
            ImageView iv = findViewById(R.id.iv);
            iv.setImageDrawable(drawable);
        }


    }
}