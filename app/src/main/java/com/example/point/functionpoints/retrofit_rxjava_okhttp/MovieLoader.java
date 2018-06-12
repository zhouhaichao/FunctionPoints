package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by 42822 on 2018/5/28.
 */

public class MovieLoader extends ObjectLoader {

    private MovieService movieService;

    public MovieLoader()
    {
        movieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }

    /**
     * 获取电影列表
     * @param start
     * @param count
     * @return
     */

    public Observable<List<Movie>> getMovie(int start, int count) {


        Observable<List<Movie>> test =observe(movieService.getTop250Get(start,count))
                .map(new PayLoad<List<Movie>>());

        return test;


       /* return observe(movieService.getTop250Get(start, count)).map(new Func1<MovieSubject, List<Movie>>() {
            @Override
            public List<Movie> call(MovieSubject movieSubject) {
                return movieSubject.getSubjects();
            }
        });*/
    }


}
