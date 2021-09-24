package com.example.android_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_lesson.animation.AnimationActivity;
import com.example.android_lesson.retrofit.RetrofitTest;
import com.example.android_lesson.rxjava.RxJavaTest;
import com.example.android_lesson.rxjavaretrofit.RxjavaRetrofitTest;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RxJavaTest().test(this);
        new RetrofitTest().test();
        new RxjavaRetrofitTest().test();


        findViewById(R.id.main_animation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });
    }
}