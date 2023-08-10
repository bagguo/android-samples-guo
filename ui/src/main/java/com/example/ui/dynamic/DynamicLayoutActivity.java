package com.example.ui.dynamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
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
        ConstraintLayout clContainer = findViewById(R.id.clContainer);

        clRoot.setPadding(116, 116, 16, 16);


        tv.setWidth(50); //not functional

        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) cl.getLayoutParams();
        lp.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        lp.height = 100;
        cl.setLayoutParams(lp);


        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.add(R.id.clContainer, new DynamicMatchHeightFragment(), "DynamicMatchHeightFragment");
        trx.commit();

        //动态设置fragment高度，解决fragment依附的view wrap，fragment不能match屏幕的情况
        ConstraintLayout.LayoutParams lpClContainer = (ConstraintLayout.LayoutParams) clContainer.getLayoutParams();
        lpClContainer.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        lpClContainer.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
        cl.setLayoutParams(lp);

    }
}