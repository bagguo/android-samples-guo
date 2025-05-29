package com.example.android_lesson.language.java.callback;

public class Client {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setCallback(new Boss());
        employee.doSome();
    }
}