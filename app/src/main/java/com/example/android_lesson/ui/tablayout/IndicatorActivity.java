package com.example.android_lesson.ui.tablayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.android_lesson.R;
import com.example.android_lesson.ui.tablayout.customtab.MyFragment;
import com.example.android_lesson.ui.tablayout.customtab.MyFragmentPagerAdapter;
import com.example.android_lesson.view.indicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class IndicatorActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, IndicatorActivity.class);
        context.startActivity(starter);
    }

    private static final int LENGTH = 4;
    List<MyFragment> fragments = new ArrayList<>();

    private DotsIndicator indicator;
    ViewPager mViewPager;
    MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        mViewPager = findViewById(R.id.vp);
        indicator = findViewById(R.id.indicator);

        for (int i = 0; i < LENGTH; i++) {
            fragments.add(MyFragment.getInstance());
        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments) {
            @NonNull
            @Override
            public Fragment getItem (int position) {
                return super.getItem(position);
            }
        };
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        indicator.attachTo(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected (int position) {

            }

            @Override
            public void onPageScrollStateChanged (int state) {

            }
        });
    }
}