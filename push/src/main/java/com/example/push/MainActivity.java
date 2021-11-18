package com.example.push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.umeng.message.PushAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (true) {//"hasAgreementAgreed"
            PushAgent.getInstance(this).onAppStart();
        }
    }
}