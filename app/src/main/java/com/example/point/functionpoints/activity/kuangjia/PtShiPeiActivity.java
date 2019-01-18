package com.example.point.functionpoints.activity.kuangjia;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;

/**
 * Created by HaiChao on 2018/12/18.
 */
public class PtShiPeiActivity extends TitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipei_pt);
        setTitleText("pt界面适配");
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080);
        //return AdaptScreenUtils.adaptHeight(super.getResources(), 1920)
    }

}
