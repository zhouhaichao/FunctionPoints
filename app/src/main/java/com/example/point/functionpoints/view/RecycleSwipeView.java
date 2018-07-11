package com.example.point.functionpoints.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.adapter.CommonSwipeRecycleViewAdapter;

/**
 * Created by HaiChao on 2018/7/11.
 */

public class RecycleSwipeView extends FrameLayout implements SwipeRecycleView.SlideListener{

    private CustomLoadMoreView swipe_load_more_footer;
    private CustomRefreshHeadView swipe_refresh_header;
    private SwipeRecycleView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;

    private CommonSwipeRecycleViewAdapter swipeRecycleViewAdapter;

    private int pageSize =10;


    public RecycleSwipeView(@NonNull Context context) {
        this(context,null);
    }

    public RecycleSwipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);

    }

    public RecycleSwipeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context)
    {
        View view = View.inflate(context, R.layout.view_recycle_swipe,null);
        this.addView(view);
        swipe_target = findViewById(R.id.swipe_target);
        swipe_load_more_footer = findViewById(R.id.swipe_load_more_footer);
        swipe_refresh_header = findViewById(R.id.swipe_refresh_header);
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//       StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //添加分割线
        //xRecycleView.addItemDecoration(new DividerItemDecoration(getContext().getApplicationContext(), DividerItemDecoration.VERTICAL));

        swipe_target.setLayoutManager(layoutManager);
        swipe_target.setSlideListener(this);
        swipe_target.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 10, 0));
    }


    public void setResultSize(int resultSize)
    {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (resultSize == 0) {
            swipe_load_more_footer.isBottom = true;
            swipeToLoadLayout.setLoadMoreEnabled(false);
        } else if (resultSize > 0 && resultSize < pageSize) {
            swipe_load_more_footer.isBottom = true;
            swipeToLoadLayout.setLoadMoreEnabled(false);
        } else if (resultSize == pageSize) {
            swipe_load_more_footer.isBottom = false;
            swipeToLoadLayout.setLoadMoreEnabled(true);
        }
    }


    public void setAdapte(CommonSwipeRecycleViewAdapter swipeRecycleViewAdapter)
    {
        this.swipeRecycleViewAdapter = swipeRecycleViewAdapter;
        swipe_target.setAdapter(swipeRecycleViewAdapter);
    }


    @Override
    public void UpDownSlide() {
        if(swipeRecycleViewAdapter!= null)
            swipeRecycleViewAdapter.clearSwipeLayout();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)
    {
        swipeToLoadLayout.setOnLoadMoreListener(onLoadMoreListener);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener)
    {
        swipeToLoadLayout.setOnRefreshListener(onRefreshListener);
    }


}
