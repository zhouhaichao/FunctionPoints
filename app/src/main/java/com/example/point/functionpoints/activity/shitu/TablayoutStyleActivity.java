package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.view.tablayout.ExTabLayout;
import com.example.point.functionpoints.view.tablayout.TabAdapter;
import com.example.point.functionpoints.view.tablayout.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiChao on 2019/1/9.
 */

//tablayout 样式界面
public class TablayoutStyleActivity extends AppCompatActivity {

    private String[] titles = new String[]{"第一个item","2","3","item4","item5","第6个item","7","8","item9","item10",};

    private ExTabLayout ex;

    @BindView(R.id.tab_design)
    android.support.design.widget.TabLayout tabDesign;
    @BindView(R.id.tab_no)
    ExTabLayout tabNo;
    @BindView(R.id.tab_my)
    ExTabLayout tabMy;
    @BindView(R.id.tab_my2)
    ExTabLayout tabMy2;
    @BindView(R.id.tab_my3)
    ExTabLayout tabMy3;
    @BindView(R.id.tab_custom)
    ExTabLayout tab_custom;
    @BindView(R.id.tab_custom2)
    TabLayout tab_custom2;//自己定义的 下划线和文字同宽，上圆角矩形的下划线
    @BindView(R.id.pager)
    ViewPager mPager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_style);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        tabDesign = findViewById(R.id.tab_design);
        ex = new ExTabLayout(this);
        ex.setTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ex.setTabIndicatorPadding(8);
        ex.setTabIndicatorStretch(1f);
        ex.setTabTextSize(32, 36);
        ex.setTabTextColors(ContextCompat.getColor(this, android.R.color.black), ContextCompat.getColor(this, R.color.colorPrimary));
        ((LinearLayout) findViewById(R.id.line)).addView(ex, 1);

        tabNo = findViewById(R.id.tab_no);

        tabMy = findViewById(R.id.tab_my);
        tabMy2 = findViewById(R.id.tab_my2);
        tabMy3 = findViewById(R.id.tab_my3);
        tab_custom = findViewById(R.id.tab_custom);
        tab_custom2 = findViewById(R.id.tab_custom2);
        mPager = findViewById(R.id.pager);



        scroll();
        for (int i = 0; i < tab_custom.getTabCount(); i++) {
            //tab_custom.getTabAt(i).setCustomView(R.layout.item);
            tabMy2.getTabAt(i).setText(titles[i]);
            tabMy3.getTabAt(i).setText(titles[i]);
            tab_custom.getTabAt(i).setText(titles[i]);
            tab_custom2.getTabAt(i).setText(titles[i]);
        }
    }


    private void scroll() {
        mPager.setAdapter(new TabAdapter(getSupportFragmentManager(), 10));

        tabDesign.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tabDesign.setupWithViewPager(mPager);

        tabNo.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tabNo.setupWithViewPager(mPager);

        ex.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        ex.setupWithViewPager(mPager);

        tabMy.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tabMy.setupWithViewPager(mPager);

        tabMy2.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tabMy2.setupWithViewPager(mPager);

        tabMy3.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tabMy3.setupWithViewPager(mPager);


        tab_custom.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tab_custom.setupWithViewPager(mPager);

        tab_custom2.setTabMode(ExTabLayout.MODE_SCROLLABLE);
        tab_custom2.setupWithViewPager(mPager);
    }
}
