package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.view.WeekView;

/**
 * Created by HaiChao on 2018/7/13.
 */

public class WeekCalendarActivity extends TitleActivity {

    WeekView wv_main;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calendar);
        setTitleText("周历--WeekCalendarActivity");
        initView();
    }

    public  void initView(){
     wv_main = findViewById(R.id.wv_main);
    }

}
