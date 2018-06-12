package com.example.point.functionpoints.retrofit_rxjava_okhttp;

/**
 * Created by 42822 on 2018/5/28.
 */

public class BaseResponse<T> {

    public int  status;
    public String message;
    public T data;
    public boolean isSuccess()
    {
        return 200 == status;
    }
}
