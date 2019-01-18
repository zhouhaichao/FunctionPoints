package com.example.point.functionpoints;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HaiChao on 2018/8/22.
 */
public class MyApplication extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //Leakcanary 内存泄露检测
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication)context.getApplicationContext();
        return application.refWatcher;
    }
}
