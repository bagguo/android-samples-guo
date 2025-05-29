package com.example.guo.language.java.callback;

public class Boss implements CallbackInterface {
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.println("收到了！！" + System.currentTimeMillis());
    }
}