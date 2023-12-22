package com.example.android_lesson.async.rxjava;

import io.reactivex.Flowable;

public class RxjavaTest {
    public void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
