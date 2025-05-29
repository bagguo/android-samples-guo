package com.example.guo.di.dagger;

import android.util.Log;

import javax.inject.Inject;

public class Engine {

    @Inject
    public Engine() {

    }

    public void run() {
        Log.i("tag", "引擎转起来了~~~ ");
    }
}
