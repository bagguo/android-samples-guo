package com.example.guo.async.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.guo.R;

public class RxjavaActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RxjavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        RxjavaSample rxjava = new RxjavaSample();
        rxjava.helloWorld();
        rxjava.chainCall();
//        rxjava.test();
        rxjava.simpleObserve();
    }
}