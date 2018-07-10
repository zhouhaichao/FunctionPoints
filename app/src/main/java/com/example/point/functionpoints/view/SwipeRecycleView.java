package com.example.point.functionpoints.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 42822 on 2018/7/10.
 */

public class SwipeRecycleView extends RecyclerView {
    public SwipeRecycleView(Context context) {
        super(context);
    }

    public SwipeRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private float startX,startY;
    private int moveStatus;//滑动状态 0是按下，1是左右滑动 ，2是上下滑动
    private SlideListener slideListener;


    public void setSlideListener(SlideListener slideListener)
    {
        this.slideListener = slideListener;
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                moveStatus = 0;
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(moveStatus==0)
                {
                    if(Math.abs(ev.getX()-startX)>Math.abs(ev.getY()-startY)&&Math.abs(ev.getX()-startX)>30)
                    {
                        moveStatus = 1;
                        Log.i("Zhou","左右滑动");
                    }
                    if(Math.abs(ev.getX()-startX)<Math.abs(ev.getY()-startY)&&Math.abs(ev.getY()-startY)>30)
                    {
                        moveStatus = 2;
                        if(slideListener!=null)
                            slideListener.UpDownSlide();
                        Log.i("Zhou","上下滑动");

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                moveStatus = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if(moveStatus==1) //判断左右滑动
        {
            return false;
        }
        else {
            return super.onInterceptTouchEvent(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }


    public  interface SlideListener
    {
        public void UpDownSlide();
    }
}

