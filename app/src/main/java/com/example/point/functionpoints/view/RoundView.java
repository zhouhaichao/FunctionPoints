package com.example.point.functionpoints.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.point.functionpoints.R;

/**
 * Created by 42822 on 2018/6/8.
 */

public class RoundView extends View {

    /**
     * 圆环宽度,默认半径的1／5
     */
    private float mRingWidth = 0;
    /**
     * 圆环颜色,默认 #CBCBCB
     */
    private int mRingColor = 0;
    /**
     * 圆背景颜色,默认 #FFFFFF
     */

    private int circleColor = 0;

    /**
     * 圆环半径,默认：Math.min(getHeight()/2,getWidth()/2)
     */
    private float mRadius = 0;
    /**
     * 底环画笔
     */
    private Paint mRingPaint,circlePaint;

    public RoundView(Context context) {
        this(context,null);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        mRingWidth = typedArray.getDimension(R.styleable.RoundView_ring_width, 0);
        mRingColor = typedArray.getColor(R.styleable.RoundView_ring_color, Color.parseColor("#CBCBCB"));
        mRadius = typedArray.getDimension(R.styleable.RoundView_radius, 0);
        circleColor =  typedArray.getColor(R.styleable.RoundView_ring_color, Color.parseColor("#FFFFFF"));
        init();
    }


    private void init() {
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);// 抗锯齿效果
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setColor(mRingColor);// 背景


        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);// 抗锯齿效果
        circlePaint.setColor(circleColor);// 背景
    }


    @Override
    public void onDraw(Canvas canvas) {
        // 圆心坐标,当前View的中心
        float x = getWidth() / 2;
        float y = getHeight() / 2;

        //如果未设置半径，则半径的值为view的宽、高一半的较小值
        float radius = mRadius == 0 ? Math.min(getWidth() / 2, getHeight() / 2) : mRadius;
        //先画园北京颜色
        canvas.drawCircle(x, y, radius, circlePaint);

        //圆环的宽度默认为半径的1／5
        float ringWidth = mRingWidth == 0 ? radius / 5 : mRingWidth;
        //由于圆环本身有宽度，所以半径要减去圆环宽度的一半，不然一部分圆会在view外面
        radius = radius - ringWidth / 2;
        mRingPaint.setStrokeWidth(ringWidth);

        // 底环
        canvas.drawCircle(x, y, radius, mRingPaint);
    }


    public void setmRingColor(int color)
    {
        mRingPaint.setColor(color);// 背景
        invalidate();
    }

}
