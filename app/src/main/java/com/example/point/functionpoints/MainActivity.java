package com.example.point.functionpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.point.functionpoints.picasso.PicassoActivity;
import com.example.point.functionpoints.retrofit_rxjava_okhttp.DoubanMovieActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.BTN_doubanMovie).setOnClickListener(this);
        findViewById(R.id.BTN_picasso).setOnClickListener(this);
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
        }
    }
}
