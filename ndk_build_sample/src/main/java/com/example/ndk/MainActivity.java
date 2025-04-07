package com.example.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("hello"); // 加载 libhello.so
    }

    public native String helloFromJNI(); // 声明 JNI 方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv);
        textView.setText(helloFromJNI()); // 调用 JNI 方法
    }
}