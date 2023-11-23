package com.example.android_lesson.dagger;

import javax.inject.Inject;

public class Car {

    @Inject //inject标记需要依赖的变量
    Engine engine;

    public Car() {
        DaggerCarComponent.builder().build().inject(this);//实现注入
    }

    public Engine getEngine() {
        return this.engine;
    }

}
