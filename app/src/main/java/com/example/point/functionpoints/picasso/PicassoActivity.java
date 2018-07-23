package com.example.point.functionpoints.picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.squareup.picasso.OkHttpDownloader;

import java.io.File;

/**
 * Created by 42822 on 2018/5/21.
 */

public class PicassoActivity extends TitleActivity implements View.OnClickListener{

    private ImageView imageView;
    private String url = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg";

    private TextView tv_size;

    private NetworkImageLoadPresenter imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        setTitleText("Picassso--毕加索");

        //这样每张图片在显示的时候，左上角都会有一个小标记，分别是 绿色、蓝色、红色三种颜色 代表 缓存，本地，网络。
        //Picasso.with(this).setIndicatorsEnabled(true);
        //通过输出日志的方式查看每张图片从网络请求加载时用的时间。
        //Picasso.with(this).setLoggingEnabled(true);

        imageView = (ImageView) findViewById(R.id.picassoView);
        tv_size = findViewById(R.id.tv_size);
        findViewById(R.id.loadImage).setOnClickListener(this);
        findViewById(R.id.cleanCurrent).setOnClickListener(this);
        findViewById(R.id.cleanAll).setOnClickListener(this);
        findViewById(R.id.calculateCache).setOnClickListener(this);

        preData();
    }

    private void preData() {
        //默认加载
        //imageLoader = NetworkImageLoadPresenter.create(this);
        //自定义Picasso
        File cacheFile = new File(getExternalCacheDir(),"cache-test");
        imageLoader = NetworkImageLoadPresenter.create(this,PicassoInfo.createPicassoInfo(this,
                new OkHttpDownloader(cacheFile),cacheFile,null));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.loadImage:
                //imageLoader.loadImage(imageView,url);
                imageLoader.loadCircleImage(imageView,url);
                break;
            case R.id.cleanCurrent:
                imageLoader.cleanCache(url);
                break;
            case R.id.cleanAll:
                imageLoader.cleanCacheAll();
                break;
            case R.id.calculateCache:
                tv_size.setText("缓存大小："+imageLoader.calculateCacheSize());
                Log.i("picasso-demo","缓存大小："+imageLoader.calculateCacheSize());
                break;
        }
    }
}
