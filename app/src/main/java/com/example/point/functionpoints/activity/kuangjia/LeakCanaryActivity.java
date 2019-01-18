package com.example.point.functionpoints.activity.kuangjia;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;

/**
 * Created by HaiChao on 2018/12/14.
 */
public class LeakCanaryActivity extends TitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        initView();
    }


    private void initView(){
        setTitleText("内存泄露检测");
        //SingletonTest.getInstance(this);//内存泄漏
    }
}
