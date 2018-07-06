package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;

/**
 * Created by bobo on 2018/7/4.
 */

public class PersonalCenterActivity extends TitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        setTitleText("个人中心");
        //setContentView();
    }
}
