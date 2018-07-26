package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 42822 on 2018/7/10.
 */

public class SwipeRecycleViewAdapter extends RecyclerView.Adapter<SwipeRecycleViewAdapter.ViewHolder>{

    private Set<SwipeListLayout> sets = new HashSet();
    public List<String> list;
    public LayoutInflater inflater;
    private Context context;


    public SwipeRecycleViewAdapter( Context context, List<String> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe_sideslip, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_content.setText(list.get(position));
        holder.tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,list.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        holder.tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,list.get(position)+"编辑",Toast.LENGTH_SHORT).show();
            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,list.get(position)+"删除",Toast.LENGTH_SHORT).show();
            }
        });
        //保证列表中只有一个侧滑状态
        holder.sll_content.setOnSwipeStatusListener(new MyOnSlipStatusListener(holder.sll_content));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_content,tv_update,tv_delete;
        SwipeListLayout sll_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            sll_content = itemView.findViewById(R.id.sll_content);
            tv_update = itemView.findViewById(R.id.tv_update);
            tv_delete = itemView.findViewById(R.id.tv_delete);

        }
    }

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

