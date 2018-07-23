package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.model.DateInfo;
import com.example.point.functionpoints.view.WeekView;

/**
 * Created by HaiChao on 2018/7/13.
 */

public class WeekCalendarActivity extends TitleActivity implements View.OnClickListener{

    WeekView wv_main;
    TextView tv_next_week,tv_last_week,tv_select;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calendar);
        setTitleText("周历--WeekCalendarActivity");
        initView();
        initEvent();
    }

    public  void initView(){
        wv_main = findViewById(R.id.wv_main);

        tv_select = findViewById(R.id.tv_select);
        tv_next_week = findViewById(R.id.tv_next_week);
        tv_last_week = findViewById(R.id.tv_last_week);
        tv_next_week.setOnClickListener(this);
        tv_last_week.setOnClickListener(this);
    }

    public void initEvent(){
        wv_main.setItemClick(new WeekView.ItemClick() {
            @Override
            public void click(int position, DateInfo selectDate) {
                tv_select.setText(selectDate.month+"月"+selectDate.day+"日");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_next_week:
                wv_main.getNextWeek();
                break;
            case R.id.tv_last_week:
                wv_main.getLastWeek();
                break;
        }
    }
}
