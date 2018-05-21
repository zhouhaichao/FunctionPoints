package com.example.point.functionpoints.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.point.functionpoints.R;
import com.squareup.picasso.OkHttpDownloader;

import java.io.File;

/**
 * Created by 42822 on 2018/5/21.
 */

public class PicassoActivity extends Activity implements View.OnClickListener{

    private ImageView imageView;
    private String url = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg";

    private NetworkImageLoadPresenter imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        imageView = (ImageView) findViewById(R.id.picassoView);
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
                imageLoader.loadImage(imageView,url);
                break;
            case R.id.cleanCurrent:
                imageLoader.cleanCache(url);
                break;
            case R.id.cleanAll:
                imageLoader.cleanCacheAll();
                break;
            case R.id.calculateCache:
                Log.i("picasso-demo","缓存大小："+imageLoader.calculateCacheSize());
                break;
        }
    }
}
