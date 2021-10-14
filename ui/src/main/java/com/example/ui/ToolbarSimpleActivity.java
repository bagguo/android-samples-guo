package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class ToolbarSimpleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_simple);

        Toolbar toolbar = findViewById(R.id.pay_toolbar);
        findViewById(R.id.toolbar_iv_back).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_iv_back) {
            finish();
        }
    }

}