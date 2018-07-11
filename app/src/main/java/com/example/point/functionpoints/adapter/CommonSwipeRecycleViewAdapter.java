package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HaiChao on 2018/7/11.
 */

public abstract class CommonSwipeRecycleViewAdapter<T> extends RecyclerView.Adapter<CommonSwipeRecycleViewAdapter.ViewHolder>{

    private Set<SwipeListLayout> sets = new HashSet();
    public List<T> list;
    public LayoutInflater inflater;
    private Context context;
    private int itemLayoutId;


    public CommonSwipeRecycleViewAdapter( Context context, List<T> list,int itemLayoutId ) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.itemLayoutId = itemLayoutId;

    }


    @Override
    public CommonSwipeRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        // 实例化viewholder
        CommonSwipeRecycleViewAdapter.ViewHolder viewHolder = new CommonSwipeRecycleViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonSwipeRecycleViewAdapter.ViewHolder holder, final int position) {
        SwipeListLayout swipeListLayout = holder.getView(R.id.sll_content);
        swipeListLayout.setOnSwipeStatusListener(new MyOnSlipStatusListener(swipeListLayout));
        convert(holder,list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;

        public ViewHolder(View itemView) {
            super(itemView);
            this.views = new SparseArray<>();
        }


        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        public View getRootView() {
            return itemView;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public void setText(int viewId, CharSequence text) {
            TextView view = getView(viewId);
            view.setText(text);
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public void setImageResource(int viewId, int drawableId) {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param bm
         * @return
         */
        public void setImageBitmap(int viewId, Bitmap bm) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
        }


    }

    public abstract void convert(CommonSwipeRecycleViewAdapter.ViewHolder holder, T item, int position);

    //所有侧滑复原，用于列表上下滑动时
    public void clearSwipeLayout(){
        if (sets.size() > 0) {
            for (SwipeListLayout s : sets) {
                s.setStatus(SwipeListLayout.Status.Close, true);
                sets.remove(s);
            }
        }
    }

    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {
        private SwipeListLayout slipListLayout;
        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
                Log.i("TestLog","tianjiabuju");
            } else {
                if (sets.contains(slipListLayout)){
                    sets.remove(slipListLayout);
                }
            }
        }

        @Override
        public void onStartCloseAnimation() {
        }

        @Override
        public void onStartOpenAnimation() {
        }

    }
}