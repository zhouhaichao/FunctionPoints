package com.example.point.functionpoints.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by bobo on 2018/7/2.
 */

public class CommonFragmentController extends FragmentPagerAdapter {


    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;

    public CommonFragmentController(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }

   /* //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }*/
}