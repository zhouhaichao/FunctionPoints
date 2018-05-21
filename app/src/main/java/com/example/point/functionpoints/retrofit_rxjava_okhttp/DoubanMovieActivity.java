package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.point.functionpoints.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 42822 on 2018/5/20.
 */

public class DoubanMovieActivity extends Activity {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private RecyclerView swipe_target;
    private MovieSubject movieSubject;
    private MovieRecycleAdapter movieRecycleAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_movie);
        swipe_target = findViewById(R.id.swipe_target);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipe_target.setLayoutManager(layoutManager);
        getData();
    }

    private void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieSubject> call = movieService.getTop250(0,20);
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
        });


    }
}
