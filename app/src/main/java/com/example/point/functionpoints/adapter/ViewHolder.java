package com.example.point.functionpoints.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bobo on 2018/6/22.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(View itemView){
        super(itemView);
    }

   /* private ViewHolder(Context context, ViewGroup parent, int layoutId) {

        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }*/



    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置是否可见
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public void setViewVisible(int viewId,int visibility){
        View view = getView(viewId);
        view.setVisibility(visibility);
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
