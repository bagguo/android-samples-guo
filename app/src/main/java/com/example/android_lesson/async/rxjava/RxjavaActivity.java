package com.example.android_lesson.async.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;

public class RxjavaActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RxjavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        new RxjavaTest().helloWorld();
    }
}