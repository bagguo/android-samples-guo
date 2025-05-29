package com.example.android_lesson.di.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android_lesson.R;

public class DaggerActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, DaggerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        Car car = new Car();
        Engine carEngine = car.getEngine();
        System.out.println("getEngin:=====" + carEngine);
        carEngine.run();
    }
}