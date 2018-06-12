package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.point.functionpoints.R;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by 42822 on 2018/5/20.
 */

public class DoubanMovieActivity extends Activity {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private RecyclerView swipe_target;
    //private MovieSubject movieSubject;
    private MovieRecycleAdapter movieRecycleAdapter;
    private LinearLayoutManager layoutManager;

    private MovieLoader movieLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_movie);
        swipe_target = findViewById(R.id.swipe_target);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipe_target.setLayoutManager(layoutManager);
        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        movieRecycleAdapter = new MovieRecycleAdapter(DoubanMovieActivity.this);
        swipe_target.setAdapter(movieRecycleAdapter);

        movieLoader =new MovieLoader();
        getMovieList();
       //getData();
    }

    /*private void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Subscription subscription = movieService.getTop250Get(0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieSubject movieSubject) {
                        movieRecycleAdapter = new MovieRecycleAdapter(DoubanMovieActivity.this,movieSubject.getSubjects());
                        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平

                        swipe_target.setAdapter(movieRecycleAdapter);
                        movieRecycleAdapter.notifyDataSetChanged();
                    }
                });*/


       /* Call<MovieSubject> call = movieService.getTop250Get(0,20);
        call.enqueue(new Callback<MovieSubject>() {
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
                movieRecycleAdapter = new MovieRecycleAdapter(DoubanMovieActivity.this,response.body().getSubjects());
                //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平

                swipe_target.setAdapter(movieRecycleAdapter);
                movieRecycleAdapter.notifyDataSetChanged();

                Log.i("haichao",response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {

            }
        });*/


    //}

    private void getMovieList()
    {
        movieLoader.getMovie(0,20)
                .subscribe(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> movies) {

                        movieRecycleAdapter.setList(movies);
                        movieRecycleAdapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TAG","error message:"+throwable.getMessage());
                        Fault fault = (Fault) throwable;
                        if(fault.getErrorCode() == 404){
                            //错误处理
                        }else if(fault.getErrorCode() == 500){
                            //错误处理
                        }else if(fault.getErrorCode() == 501){
                            //错误处理
                        }
                        Log.e("TAG","error message:"+fault.getErrorCode());

                    }
                });
    }
}
