package com.example.android_lesson.input;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.example.android_lesson.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SoftInputSampleActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input_sample_java);
        FrameLayout rooFl = findViewById(R.id.fl_root);
        rooFl.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View view, @NonNull WindowInsets windowInsets) {
                return null;
            }
        });

    }




}