package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.view.ClockView;

/**
 * Created by HaiChao on 2019/5/14.
 */
public class ClockViewActivity extends TitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_view);

        final ClockView clockView =findViewById(R.id.cv_test);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.setAngle(180);
                clockView.start(5000);
            }
        });

    }




}
