package com.example.point.functionpoints.retrofit_rxjava_okhttp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 42822 on 2018/5/20.
 */

public class MovieRecycleAdapter extends RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder> {

    private Context context;
    private List<Movie> list;


    public MovieRecycleAdapter( Context context)
    {
        this.context = context;
    }

    public MovieRecycleAdapter( Context context, List<Movie> list)
    {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_info,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.TV_title.setText(list.get(position).getTitle());
        holder.TV_originalName.setText(list.get(position).getOriginal_title());
        holder.TV_year.setText(list.get(position).getYear());
        //NetworkImageLoadPresenter.create(context).loadCircleImage(holder.IMV_image,list.get(position).getImages().getSmall());
        Picasso.with(context)
                .load(list.get(position).getImages().getSmall())
                .into(holder.IMV_image);
        //holder.IMV_image
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView TV_title;
        TextView TV_originalName;
        TextView TV_year;
        ImageView IMV_image;


        public ViewHolder(View itemView) {
            super(itemView);
            TV_title = itemView.findViewById(R.id.TV_title);
            TV_originalName = itemView.findViewById(R.id.TV_originalName);
            TV_year = itemView.findViewById(R.id.TV_year);
            IMV_image = itemView.findViewById(R.id.IMV_image);
        }
    }

}
