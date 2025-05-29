package com.example.guo.base.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by DB_BOY on 2019/3/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final int CODE_CLICK = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Log.d("TAG", "onCreate: ----" + this.getClass().getName());
        initData();
        initView();
        setListener();
    }

    public void initData() {

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    public void setListener() {

    }

}
