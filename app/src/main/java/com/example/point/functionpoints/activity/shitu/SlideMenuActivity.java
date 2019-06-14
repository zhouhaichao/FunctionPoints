package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;

/**
 * Created by HaiChao on 2019/2/12.
 */
public class SlideMenuActivity extends TitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        setTitleText("个人中心");
        //setContentView();
    }

}
