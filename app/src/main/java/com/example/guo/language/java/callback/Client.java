package com.example.guo.language.java.callback;

public class Client {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setCallback(new Boss());
        employee.doSome();
    }
}