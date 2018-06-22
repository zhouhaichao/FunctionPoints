package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 42822 on 2018/5/28.
 */

public class HttpCommonInterceptor implements Interceptor {

    private Map<String,String> mHeaderParamsMap = new HashMap<>();


    @Override
    public Response intercept(Chain chain) throws IOException {

        Log.d("HttpCommonInterceptor","add common params");
        Request oldRequest = chain.request();

        Request.Builder requestBuilder =  oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(),oldRequest.body());

        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }

        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);

    }

    public static class Builder
    {
        HttpCommonInterceptor httpCommonInterceptor;

        public Builder()
        {
            httpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key ,String value)
        {
            httpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }


        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build()
        {
            return httpCommonInterceptor;
        }

    }


}
