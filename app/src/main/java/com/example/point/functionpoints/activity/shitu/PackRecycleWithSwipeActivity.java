package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.adapter.CommonSwipeRecycleViewAdapter;
import com.example.point.functionpoints.view.RecycleSwipeView;

import java.util.ArrayList;

/**
 * Created by HaiChao on 2018/7/11.
 */

public class PackRecycleWithSwipeActivity extends TitleActivity implements OnRefreshListener,OnLoadMoreListener{

    private RecycleSwipeView rsv_content;
    private  ArrayList<String> strlist;
    CommonSwipeRecycleViewAdapter commonSwipeRecycleViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_swipe_pack);

        setTitleText("PackRecycleWithSwipeActivity");
        initView();
        initData();
    }

    public void initView()
    {
        rsv_content = findViewById(R.id.rsv_content);
        rsv_content.setOnLoadMoreListener(this);
        rsv_content.setOnRefreshListener(this);

    }

    public void initData()
    {
        strlist = new ArrayList();
        for(int i = 0 ;i<10;i++)
        {
            strlist.add("封装---正文"+i);
        }
        commonSwipeRecycleViewAdapter = new CommonSwipeRecycleViewAdapter<String>(this,strlist,R.layout.item_swipe_sideslip) {
            @Override
            public void convert(ViewHolder holder, final String item, int position) {

                holder.setText(R.id.tv_content,item);
                holder.getView(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(PackRecycleWithSwipeActivity.this,item,Toast.LENGTH_SHORT).show();
                    }
                });

                holder.getView(R.id.tv_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(PackRecycleWithSwipeActivity.this,item+"更新",Toast.LENGTH_SHORT).show();
                    }
                });
                holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(PackRecycleWithSwipeActivity.this,item+"删除",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        rsv_content.setAdapte(commonSwipeRecycleViewAdapter);
    }

    @Override
    public void onLoadMore() {

        if(strlist!=null)
        {
            ArrayList<String> addstr = new ArrayList<>();
            for (int i = strlist.size() ;i<strlist.size()+10;i++)
            {
                if(i<35)
                    addstr.add("封装---正文"+i);
            }
            strlist.addAll(addstr);
            rsv_content.setResultSize(addstr.size());
            commonSwipeRecycleViewAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {
        if(strlist==null)
            strlist =new ArrayList<>();
        strlist.clear();
        ArrayList<String> addstr = new ArrayList<>();
        for(int i = 0 ;i<10;i++)
        {
            addstr.add("封装---正文"+i);
        }
        strlist.addAll(addstr);
        rsv_content.setResultSize(addstr.size());
        commonSwipeRecycleViewAdapter.notifyDataSetChanged();
    }
}
