package com.example.android_lesson.ui.recyclerview.expandable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_lesson.R;

public class BaseRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_view);
    }


}