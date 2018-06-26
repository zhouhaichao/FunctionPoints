package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.point.functionpoints.R;

import java.util.List;

/**
 * Created by bobo on 2018/6/22.
 */

public abstract class CommonRecycleAdapter<T>  extends RecyclerView.Adapter<ViewHolder>{

    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonRecycleAdapter(Context context, List<T> mDatas, int itemLayoutId)
    {
//        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }


    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_info,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder,mDatas.get(position),position);
    }

    public abstract void convert(ViewHolder helper, T item, int position);
}
