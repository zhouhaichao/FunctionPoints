package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 42822 on 2018/5/28.
 */

public class ObjectLoader {

    /**
     *
     * @param observable
     * @param <T>
     * @return
     */


    public <T> Observable<T> observe(Observable<T> observable)
    {
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
