package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 42822 on 2018/5/20.
 */

public interface MovieService {

    //获取豆瓣Top250榜单电影信息
    @GET("top250")
    Call<MovieSubject> getTop250(@Query("start")int start, @Query("count")int count);
}
