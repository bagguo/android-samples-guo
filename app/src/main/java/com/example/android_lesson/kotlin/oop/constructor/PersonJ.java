package com.example.android_lesson.kotlin.oop.constructor;

public class PersonJ {

    String name;
    String age;

    public PersonJ(String name) {
        this.name = name;
    }

    public PersonJ(String name, String age) {
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
