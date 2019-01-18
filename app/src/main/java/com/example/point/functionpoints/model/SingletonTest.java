package com.example.point.functionpoints.model;

import android.content.Context;

/**
 * Created by HaiChao on 2018/12/14.
 */
//单例模型 测试内存泄漏检测 正确的是 见SingletonTest2类
public class SingletonTest {

    private Context mContext;
    private static SingletonTest instance;

    private SingletonTest(Context context){
        mContext = context;
    }

    public static synchronized SingletonTest getInstance(Context context){
        if(instance == null){
            instance = new SingletonTest(context);
        }
        return instance;
    }
}
