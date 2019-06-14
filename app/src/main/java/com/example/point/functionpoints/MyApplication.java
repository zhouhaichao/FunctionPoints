package com.example.point.functionpoints;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HaiChao on 2018/8/22.
 */
public class MyApplication extends Application {

    private RefWatcher refWatcher;

    private ApplicationLike tinkerApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //Leakcanary 内存泄露检测
        refWatcher = LeakCanary.install(this);

        //Gloading.initDefault(new G);

        //热更新
        initTinker();



    }

    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication)context.getApplicationContext();
        return application.refWatcher;
    }

    private void initTinker(){
        if (BuildConfig.TINKER_ENABLE) {
            // 我们可以从这里获得Tinker加载过程的信息
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

            // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
            TinkerPatch.init(tinkerApplicationLike)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true);


            TinkerPatch.with().fetchPatchUpdate(true);
            // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            //new FetchPatchHandler().fetchPatchWithInterval(3);
        }
    }
}
