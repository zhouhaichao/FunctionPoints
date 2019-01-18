package com.example.point.functionpoints.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.point.functionpoints.MyApplication;
import com.example.point.functionpoints.R;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by bobo on 2018/7/6.
 */

public class TitleActivity extends Activity {

        TextView tv_title,tv_left_text,tv_right_text;
        ImageView iv_left_image,iv_right_image;

        LinearLayout ll_right,ll_left;

        @Override
        public void setContentView(int layoutResID) {
            super.setContentView(layoutResID);
            tv_title = findViewById(R.id.tv_title);
            tv_left_text = findViewById(R.id.tv_left_text);
            tv_right_text = findViewById(R.id.tv_right_text);
            iv_left_image = findViewById(R.id.iv_left_image);
            iv_right_image = findViewById(R.id.iv_right_image);
            ll_right = findViewById(R.id.ll_right);
            ll_left = findViewById(R.id.ll_left);

            ll_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    protected TitleActivity setTitleText(String title)
    {
        tv_title.setText(title);
        return this;
    }

    protected TitleActivity setLeftText(String leftText)
    {
        tv_left_text.setVisibility(View.VISIBLE);
        tv_left_text.setText(leftText);
        return this;
    }

    protected TitleActivity setRightText(String rightText)
    {
        tv_right_text.setVisibility(View.VISIBLE);
        tv_right_text.setText(rightText);
        return this;
    }

    protected TitleActivity setRightImage(int ImageRes, View.OnClickListener onClickListener)
    {
        iv_right_image.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(ImageRes);
        ll_right.setOnClickListener(onClickListener);
        return this;
    }

    protected TitleActivity setLeftImage(int ImageRes,View.OnClickListener onClickListener)
    {
        iv_left_image.setVisibility(View.VISIBLE);
        iv_left_image.setImageResource(ImageRes);
        ll_left.setOnClickListener(onClickListener);
        return this;
    }


}
