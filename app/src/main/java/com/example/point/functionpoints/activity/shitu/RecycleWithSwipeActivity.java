package com.example.point.functionpoints.activity.shitu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.adapter.SwipeRecycleViewAdapter;
import com.example.point.functionpoints.view.CustomLoadMoreView;
import com.example.point.functionpoints.view.CustomRefreshHeadView;
import com.example.point.functionpoints.view.RecycleViewDivider;
import com.example.point.functionpoints.view.SwipeRecycleView;

import java.util.ArrayList;

/**
 * Created by 42822 on 2018/7/10.
 */

public class RecycleWithSwipeActivity extends TitleActivity  implements OnRefreshListener,OnLoadMoreListener,SwipeRecycleView.SlideListener{

    private SwipeRecycleView swipe_target;
    private CustomLoadMoreView swipe_load_more_footer;
    private CustomRefreshHeadView swipe_refresh_header;


    private SwipeRecycleViewAdapter swipeRecycleViewAdapter;

    private SwipeToLoadLayout swipeToLoadLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_swipe);
        setTitleText("RecycleWithSwipeActivity");
        initView();
        initData();

    }

    public void initView()
    {
        swipe_target = findViewById(R.id.swipe_target);
        swipe_load_more_footer = findViewById(R.id.swipe_load_more_footer);
        swipe_refresh_header = findViewById(R.id.swipe_refresh_header);
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);


        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //添加分割线
        //xRecycleView.addItemDecoration(new DividerItemDecoration(getContext().getApplicationContext(), DividerItemDecoration.VERTICAL));

        swipe_target.setLayoutManager(layoutManager);
        swipe_target.setSlideListener(this);

        swipe_target.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 10, 0));
    }

    public void initData()
    {
        ArrayList<String> strlist= new ArrayList();
        for(int i = 0 ;i<10;i++)
        {
            strlist.add("正文"+i);
        }

        swipeRecycleViewAdapter = new SwipeRecycleViewAdapter(this,strlist);
        swipe_target.setAdapter(swipeRecycleViewAdapter);
    }

    @Override
    public void onLoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = handler.obtainMessage();
                    message.what =2;
                    handler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = handler.obtainMessage();
                    message.what =1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    swipeToLoadLayout.setRefreshing(false);
                    break;
                case 2:
                    swipeToLoadLayout.setLoadingMore(false);

                    break;
            }
        }
    };

    @Override
    public void UpDownSlide() {
        swipeRecycleViewAdapter.clearSwipeLayout();
    }
}
