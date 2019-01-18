package com.example.point.functionpoints.model;

import android.content.Context;

/**
 * Created by HaiChao on 2018/12/14.
 */
//正确的静态内部类
public class SingletonTest2 {

    private Context context;
    private static SingletonTest2 instance;

    private SingletonTest2(Context context){
        this.context = context.getApplicationContext();

    }
    /** 对外提供公共的访问方法 */

    public static SingletonTest2 getInstance(Context context) {

        if(instance == null){
            instance = new SingletonTest2(context);
        }
        return instance;
    }

}
