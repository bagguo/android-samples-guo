package com.example.android_lesson.memory;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android_lesson.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MemorySamplesActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, MemorySamplesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_samples);

        List<C> listA = new ArrayList<>();
        List<C> listB = new ArrayList<>();
        listA.add(new C());
        listB.add(new C());

        C c1 = listA.get(0);
        c1 = null;
        C c2 = (C) listB.get(0);
        Log.d("TAG", "onCreate: ======c1" + c1);
        Log.d("TAG", "onCreate: ======c2" + c2);//结论：c2不会为null

    }
}

