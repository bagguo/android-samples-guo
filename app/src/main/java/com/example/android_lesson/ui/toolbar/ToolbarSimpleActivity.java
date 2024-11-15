package com.example.android_lesson.ui.toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android_lesson.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ToolbarSimpleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ToolbarSimpleActivity";

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
            Log.i(TAG, "onClick: ====");
            setResult(RESULT_OK);
//            finish();
        }
    }

}