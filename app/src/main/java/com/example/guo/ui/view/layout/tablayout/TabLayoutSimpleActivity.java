package com.example.guo.ui.view.layout.tablayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.guo.R;
import com.example.guo.ui.view.layout.tablayout.customtab.MyFragment;
import com.example.guo.ui.view.layout.tablayout.customtab.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutSimpleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TabLayoutSimpleActivity.class);
        context.startActivity(starter);
    }

    public static final int LENGTH = 4;
    List<MyFragment> fragments = new ArrayList<>();

    TabLayout mTabLayout;
    ViewPager mViewPager;
    MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_simple);

        for (int i = 0; i < LENGTH; i++) {
            fragments.add(MyFragment.getInstance());
        }

        initTab();
    }

    protected void initTab() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);//tablayout与viewpager关联




        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}