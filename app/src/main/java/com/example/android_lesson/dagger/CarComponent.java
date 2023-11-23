package com.example.android_lesson.dagger;

import dagger.Component;

@Component
public interface CarComponent {//其实就是一个注入器

    void inject(Car car);//这里用来将Engine注入到Car中
}
