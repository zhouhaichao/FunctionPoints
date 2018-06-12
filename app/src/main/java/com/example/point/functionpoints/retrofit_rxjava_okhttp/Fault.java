package com.example.point.functionpoints.retrofit_rxjava_okhttp;

/**
 * Created by 42822 on 2018/5/28.
 */

public class Fault extends RuntimeException{

    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
