package com.example.point.functionpoints.retrofit_rxjava_okhttp;

/**
 * Created by 42822 on 2018/5/28.
 */

public class BaseResponse<T> {


    /*//规范模式
    public int  status;
    public String message;
    public T data;
    public boolean isSuccess()
    {
        return 200 == status;
    }*/

    //针对豆瓣电影网的返回数据设置

    public T subjects;
    public boolean isSuccess()
    {
        return true;
    }
}
