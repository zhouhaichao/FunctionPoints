package com.example.point.functionpoints.view;

import android.content.Context;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by 42822 on 2018/7/10.
 */

public class CustomLoadMoreView extends android.support.v7.widget.AppCompatTextView implements SwipeLoadMoreTrigger, SwipeTrigger {

    public boolean isBottom = false;

    public CustomLoadMoreView(Context context) {
        super(context);
    }

    public CustomLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
        if(isBottom)
            setText("已经到底了");
        else
            setText("正在加载中...");
    }

    @Override
    public void onPrepare() {
        if(isBottom)
            setText("已经到底了");
        else
            setText("上拉加载更多");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {}

    @Override
    public void onRelease() {}

    @Override
    public void onComplete() {
        if(isBottom)
            setText("已经到底了");
        else
            setText("加载成功");
    }

    @Override
    public void onReset() {

    }
}