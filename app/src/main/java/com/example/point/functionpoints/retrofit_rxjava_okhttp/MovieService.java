package com.example.point.functionpoints.retrofit_rxjava_okhttp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 42822 on 2018/5/20.
 */

public interface MovieService {

    //获取豆瓣Top250榜单电影信息
    @GET("top250")
    Observable<BaseResponse<List<Movie>>> getTop250Get(@Query("start")int start, @Query("count")int count);


    @FormUrlEncoded
    @POST
    Call<MovieSubject> getTop250Post(@Field("start")int start,@Field("count")int count);
}
