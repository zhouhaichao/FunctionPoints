package com.example.point.functionpoints.retrofit_rxjava_okhttp;


import rx.functions.Func1;

/**
 * Created by 42822 on 2018/5/28.
 */

public class PayLoad<T> implements Func1<BaseResponse<T>,T> {

    @Override
    public T call(BaseResponse<T> tBaseResponse) {
        if(!tBaseResponse.isSuccess())
        {
            throw  new Fault(tBaseResponse.status,tBaseResponse.message);
        }
        return tBaseResponse.data;
    }
}
