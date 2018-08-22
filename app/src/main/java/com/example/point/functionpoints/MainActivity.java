package com.example.point.functionpoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.point.functionpoints.activity.KuangJiaActivity;
import com.example.point.functionpoints.activity.ListTreeActivity;
import com.example.point.functionpoints.activity.ShiTuActivity;
import com.example.point.functionpoints.picasso.PicassoActivity;
import com.example.point.functionpoints.retrofit_rxjava_okhttp.DoubanMovieActivity;
import com.example.point.functionpoints.view.RoundView;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.BTN_doubanMovie).setOnClickListener(this);
        findViewById(R.id.BTN_picasso).setOnClickListener(this);
        findViewById(R.id.BTN_changeColor).setOnClickListener(this);
        findViewById(R.id.BTN_list_tree).setOnClickListener(this);
        findViewById(R.id.tv_kuangjia).setOnClickListener(this);
        findViewById(R.id.tv_shitu).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.BTN_doubanMovie:
                intent = new Intent(MainActivity.this, DoubanMovieActivity.class);
                startActivity(intent);
                break;
            case R.id.BTN_picasso:
                intent = new Intent(MainActivity.this, PicassoActivity.class);
                startActivity(intent);
                break;
            case R.id.BTN_changeColor:
                ((RoundView)findViewById(R.id.RV_test)).setmRingColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.BTN_list_tree:
                intent = new Intent(MainActivity.this, ListTreeActivity.class);
                startActivity(intent);
            case R.id.tv_kuangjia:
                intent = new Intent(MainActivity.this, KuangJiaActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_shitu:
                intent = new Intent(MainActivity.this, ShiTuActivity.class);
                startActivity(intent);
                break;

        }
    }
}
