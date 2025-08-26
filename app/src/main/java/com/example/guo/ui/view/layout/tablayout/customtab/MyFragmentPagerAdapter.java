package com.example.guo.ui.view.layout.tablayout.customtab;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<MyFragment> mFragments;
//    private String[] mTitles;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, List<MyFragment> fragments/*, String[] titles*/) {
        super(fm);
        mFragments = fragments;
//        mTitles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitles[position];
//    }

}
