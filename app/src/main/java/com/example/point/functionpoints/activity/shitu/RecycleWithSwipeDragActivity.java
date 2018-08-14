package com.example.point.functionpoints.activity.shitu;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.adapter.SwipeRecycleViewAdapter;
import com.example.point.functionpoints.view.CustomLoadMoreView;
import com.example.point.functionpoints.view.CustomRefreshHeadView;
import com.example.point.functionpoints.view.RecycleViewDivider;
import com.example.point.functionpoints.view.SwipeListLayout;
import com.example.point.functionpoints.view.SwipeRecycleView;

import java.util.ArrayList;

/**
 * Created by HaiChao on 2018/7/26.
 */

public class RecycleWithSwipeDragActivity extends TitleActivity implements OnRefreshListener,OnLoadMoreListener,SwipeRecycleView.SlideListener {

    private SwipeRecycleView swipe_target;
    private CustomLoadMoreView swipe_load_more_footer;
    private CustomRefreshHeadView swipe_refresh_header;


    private SwipeRecycleViewAdapter swipeRecycleViewAdapter;

    private SwipeToLoadLayout swipeToLoadLayout;
    ArrayList<String> strlist= new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_swipe);
        setTitleText("RecycleWithSwipeDragActivity");
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

        for(int i = 0 ;i<10;i++)
        {
            strlist.add("正文"+i);
        }

        swipeRecycleViewAdapter = new SwipeRecycleViewAdapter(this,strlist);
        swipe_target.setAdapter(swipeRecycleViewAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {

            //getMovementFlags用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向，比如如果是列表类型的RecyclerView，拖拽只有UP、DOWN两个方向，而如果是网格类型的则有UP、DOWN、LEFT、RIGHT四个方向
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                SwipeListLayout sll_content = viewHolder.itemView.findViewById(R.id.sll_content);
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                if(sll_content!=null&&sll_content.isOpen())
                    return makeMovementFlags(dragFlags, 0);
                else
                    return makeMovementFlags(dragFlags, ItemTouchHelper.RIGHT );
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //Collections.swap(strlist,viewHolder.getAdapterPosition(),target.getAdapterPosition());//数据源交换
                swipeRecycleViewAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());//通知适配器
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                /*strlist.remove(viewHolder.getAdapterPosition());
                swipeRecycleViewAdapter.notifyDataSetChanged();*/

                swipeRecycleViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //获取系统震动服务
                Vibrator vib = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
                //震动70毫秒
                vib.vibrate(70);
                return super.isLongPressDragEnabled();
            }

            @Override
            public boolean isItemViewSwipeEnabled() {

                return super.isItemViewSwipeEnabled();
            }
        });

        //将recycleView和ItemTouchHelper绑定
        touchHelper.attachToRecyclerView(swipe_target);
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
