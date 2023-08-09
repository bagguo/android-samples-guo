package com.example.ui.dynamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ui.R;
import com.example.ui.UIMainActivity;
import com.example.ui.listview.ListViewDemoActivity;

public class DynamicLayoutActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, DynamicLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_layout);

        ConstraintLayout clRoot = findViewById(R.id.clRoot);
        ConstraintLayout cl = findViewById(R.id.cl);
        TextView tv = findViewById(R.id.tv);

        clRoot.setPadding(116, 116, 16, 16);


        tv.setWidth(50); //not functional

        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) cl.getLayoutParams();
        lp.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        lp.height = 100;
        cl.setLayoutParams(lp);

    }
}