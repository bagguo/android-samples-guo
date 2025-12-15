package com.example.guo.ui;


import android.os.Bundle;

import com.example.guo.R;

import androidx.appcompat.app.AppCompatActivity;

public class ButterKnifeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife_test);
        //使用butterKnife进行绑定，很重要，不然click等等绑定事件不起作用
//        ButterKnife.bind(this);
    }

    // ButterKnife 用 binding 替代
//    @OnClick(R2.id.button)
//    void click() {
//        Log.i("aaaaaaaaaa", "点击了butter knife 绑定的view");
//        Toast.makeText(this, "点击了butter knife 绑定的view", Toast.LENGTH_SHORT).show();
//    }
}