package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UIMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uimain);

        findViewById(R.id.toolbar_simple).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, ToolbarSimpleActivity.class);
            startActivity(intent);
        });
    }
}