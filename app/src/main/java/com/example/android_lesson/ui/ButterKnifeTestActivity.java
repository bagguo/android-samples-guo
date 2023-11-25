package com.example.android_lesson.ui;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android_lesson.R;
import com.example.android_lesson.R2;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife_test);
        //使用butterKnife进行绑定，很重要，不然click等等绑定事件不起作用
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.button) void click(){
        Log.i("aaaaaaaaaa","点击了butter knife 绑定的view");
        Toast.makeText(this, "点击了butter knife 绑定的view", Toast.LENGTH_SHORT).show();
    }
}