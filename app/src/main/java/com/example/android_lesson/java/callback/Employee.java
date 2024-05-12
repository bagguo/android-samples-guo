package com.example.android_lesson.java.callback;

public class Employee{
    CallbackInterface callback = null;

    public void setCallback (CallbackInterface callback) {
        this.callback = callback;
    }

    public void doSome () {
        for (int i = 0; i < 10; i++) {
         System.out.println("第【" + i + "】件事完成了");
         }
         callback.execute();
    }

 }