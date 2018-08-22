package com.example.point.functionpoints;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HaiChao on 2018/8/22.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
