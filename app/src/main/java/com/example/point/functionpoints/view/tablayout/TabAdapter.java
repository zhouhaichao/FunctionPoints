package com.example.point.functionpoints.view.tablayout;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.point.functionpoints.fragment.TabViewFragment;

/**
 * Created by HaiChao on 2019/1/9.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private int size;

    public TabAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size=size;
    }


    @Override
    public Fragment getItem(int position) {
        return new TabViewFragment();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Title" + position;
    }
}
