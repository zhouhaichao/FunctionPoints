package com.example.point.functionpoints.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.point.functionpoints.model.DateInfo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HaiChao on 2018/7/13.
 */

public class WeekView extends ViewGroup implements View.OnClickListener{

    private static final String TAG = "WeekView";
    private ArrayList<DateInfo> datelist;
    private Calendar calendar;
    private DateInfo selectDate;

    private ItemClick itemClick;

    public WeekView(Context context) {
        this(context,null);
    }

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

public  void setItemClick(ItemClick itemClick)
{
    this.itemClick = itemClick;
}

    public void  initData()
    {
        datelist = new ArrayList<>();
        selectDate = new DateInfo();
        printWeekdays();
    }

    private  void printWeekdays() {
        calendar = Calendar.getInstance();
        selectDate.month = calendar.get(Calendar.MONTH)+1;
        selectDate.day = calendar.get(Calendar.DAY_OF_MONTH);
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            DateInfo dateInfo = new DateInfo();
            dateInfo.month = calendar.get(Calendar.MONTH)+1;
            dateInfo.day = calendar.get(Calendar.DAY_OF_MONTH);
            datelist.add(dateInfo);
            calendar.add(Calendar.DATE, 1);
        }
    }

    public void getNextWeek()
    {
        datelist.clear();
        for (int i = 0; i < 7; i++) {
            DateInfo dateInfo = new DateInfo();
            dateInfo.month = calendar.get(Calendar.MONTH)+1;
            dateInfo.day = calendar.get(Calendar.DAY_OF_MONTH);
            datelist.add(dateInfo);
            calendar.add(Calendar.DATE, 1);
        }
        refreshView();
    }

    public void getLastWeek()
    {
        datelist.clear();
        calendar.add(Calendar.DATE, -14);
        for (int i = 0; i < 7; i++) {
            DateInfo dateInfo = new DateInfo();
            dateInfo.month = calendar.get(Calendar.MONTH)+1;
            dateInfo.day = calendar.get(Calendar.DAY_OF_MONTH);
            datelist.add(dateInfo);
            calendar.add(Calendar.DATE, 1);
        }
        refreshView();
    }


    private  void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    //获取本周的天数据
   /* public ArrayList<Calendar> getDaysInWeek()
    {
        Calendar today = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        today.setTime(date);

        ArrayList<Calendar> days = new ArrayList<>();
        int w = today.get(Calendar.DAY_OF_WEEK);
        for(int i = 0 ;i<days.size()-1;i++)
        {
            if(i==0)
            {
                today.add(Calendar.DAY_OF_MONTH,w-1);
                days.add(today);
            }
            else
            {

            }


        }
    }*/

   public void refreshView()
   {
       int cnt = getChildCount();
       for(int i = 0; i < cnt; i++) {
           View child = getChildAt(i);
           ((TextView)child).setText(datelist.get(i).month+""+datelist.get(i).day);
       }
   }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int maxSize = widthSize / 7;
        int baseSize = 0;

        int cnt = getChildCount();
        for(int i = 0; i < cnt; i++) {

            View child = getChildAt(i);

            if(child.getVisibility() == View.GONE) {
                continue;
            }

            child.measure(
                    MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.AT_MOST)
            );

            baseSize = Math.max(baseSize, child.getMeasuredHeight());

        }

        for (int i = 0; i < cnt; i++) {

            View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            child.measure(
                    MeasureSpec.makeMeasureSpec(baseSize, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(baseSize, MeasureSpec.EXACTLY)
            );

        }
        setMeasuredDimension(widthSize, getLayoutParams().height >= 0 ? getLayoutParams().height : baseSize + getPaddingBottom() + getPaddingTop());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cnt = getChildCount();

        int width = getMeasuredWidth();
        int part = width / cnt;

        for(int i = 0; i < cnt; i++) {
            View child = getChildAt(i);
            child.setTag(i);
            child.setOnClickListener(this);

            ((TextView)child).setText(datelist.get(i).month+""+datelist.get(i).day);
            if(child.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth();

            int x = i * part + ((part - childWidth) / 2);
            child.layout(x, 0, x + childWidth, child.getMeasuredHeight());

        }

    }

    @Override
    public void onClick(View view) {
       int positon = (int) view.getTag();
       if(positon<datelist.size())
       {
           selectDate.month = datelist.get(positon).month;
           selectDate.day = datelist.get(positon).day;
           if(itemClick!=null)
           {
               itemClick.click(positon,selectDate);
           }
       }
    }

    public  interface  ItemClick
    {
        void click(int position, DateInfo selectDate);
    }
}