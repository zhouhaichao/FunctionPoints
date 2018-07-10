package com.example.point.functionpoints.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by 42822 on 2018/7/10.
 */

public class CustomRefreshHeadView extends android.support.v7.widget.AppCompatTextView implements SwipeRefreshTrigger, SwipeTrigger {
    public CustomRefreshHeadView(Context context) {
        super(context);
    }

    public CustomRefreshHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRefreshHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {
        //Log.i("TestLog","onRefresh");
        setText("正在加载数据...");
    }

    @Override
    public void onPrepare() {
        setText("下拉刷新");
        //Log.i("TestLog","onPrepare");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        // Log.i("TestLog","onMove");
    }

    @Override
    public void onRelease() {
        Log.i("TestLog","onRelease");
    }

    @Override
    public void onComplete() {
        //Log.i("TestLog","onComplete");
        setText("刷新成功");
    }

    @Override
    public void onReset() {
        //Log.i("TestLog","onReset");

    }

}
