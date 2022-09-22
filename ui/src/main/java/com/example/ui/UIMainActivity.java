package com.example.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.ui.butterknife.ButterKnifeTestActivity;
import com.example.ui.dialog.DialogDemoActivity;
import com.example.ui.gridview.GridViewDemoActivity;
import com.example.ui.multitype.normal.NormalActivity;
import com.example.ui.tablayout.TabLayoutDemoActivity;
import com.example.ui.toolbar.ToolbarSimpleActivity;

import androidx.appcompat.app.AppCompatActivity;

public class UIMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uimain);

        findViewById(R.id.toolbar_simple).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, ToolbarSimpleActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_grid_view).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, GridViewDemoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_tab_layout).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, TabLayoutDemoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_butter_knife).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, ButterKnifeTestActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_multi_type).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, NormalActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_dialog).setOnClickListener(view -> {
            Intent intent = new Intent(UIMainActivity.this, DialogDemoActivity.class);
            startActivity(intent);
        });
    }
}