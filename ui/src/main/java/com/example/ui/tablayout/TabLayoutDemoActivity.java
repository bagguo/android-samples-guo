package com.example.ui.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ui.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutDemoActivity extends AppCompatActivity {

    private String[] titles = {"推荐", "活跃", "新人", "附近", "关注"};
    private String[] titles2 = {"2推荐2", "2活跃2", "2新人2", "2附近2", "2关注2"};
    List<MyFragment> fragments = new ArrayList<>();

    TabLayout mTabLayout;
    ViewPager mViewPager;
    MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_demo);

        for (int i = 0; i < titles.length; i++) {
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

        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                assert view != null;
                Drawable drawable = ContextCompat.getDrawable(TabLayoutDemoActivity.this
                        , R.drawable.common_bg_round_primary);
                view.findViewById(R.id.tab_item_tv2).setBackground(drawable);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                assert view != null;
                view.findViewById(R.id.tab_item_tv2).setBackground(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView tv = view.findViewById(R.id.tab_item_tv);
        TextView tv2 = view.findViewById(R.id.tab_item_tv2);
        if (position == 1) {
            tv.setText(null);
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background);
            assert drawable != null;
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv.setCompoundDrawables(drawable, null, null, null);
        } else {
            tv.setText(titles[position]);
        }
        tv2.setText(titles2[position]);
        return view;
    }
}