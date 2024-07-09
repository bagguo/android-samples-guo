package com.example.android_lesson.kotlin.constructor;

public class Person2 {

    String name;
    String age;

    public Person2(String name) {
        this.name = name;
    }

    public Person2(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

}
