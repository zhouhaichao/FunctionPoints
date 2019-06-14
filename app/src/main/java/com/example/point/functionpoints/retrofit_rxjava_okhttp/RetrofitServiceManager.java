package com.example.point.functionpoints.retrofit_rxjava_okhttp;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 42822 on 2018/5/28.
 */

public class RetrofitServiceManager {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;

    private Retrofit mRetrofit;

    private RetrofitServiceManager()
    {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);

        // 添加公共参数拦截器
        HttpCommonInterceptor httpCommonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform","android")
                .addHeaderParams("userToken","1234343434dfdfd3434")
                .addHeaderParams("userId","123445")
                .addHeaderParams("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                .build();

        // 创建Retrofit
        builder.addInterceptor(httpCommonInterceptor);

        mRetrofit =new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static  class SingletonHolder
    {
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    public static RetrofitServiceManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */

    public <T> T create(Class<T> service)
    {
        return mRetrofit.create(service);
    }


}
