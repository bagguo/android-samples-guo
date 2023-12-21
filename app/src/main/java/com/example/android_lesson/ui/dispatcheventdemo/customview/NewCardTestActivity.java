package com.example.android_lesson.ui.dispatcheventdemo.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android_lesson.R;

public class NewCardTestActivity extends AppCompatActivity {

    public static final String TAG = NewCardTestActivity.class.getSimpleName();

    private View mCardView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card_test);

        mCardView = findViewById(R.id.card_view);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            Log.d(TAG, "onCreate: ==========btn Clicked");
        });
    }
}