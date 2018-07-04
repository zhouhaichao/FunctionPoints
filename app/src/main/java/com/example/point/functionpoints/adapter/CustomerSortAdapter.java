package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.model.DeptPersonnelTree;
import com.example.point.functionpoints.util.RandomImage;

import java.util.List;

/**
 * Created by bobo on 2018/7/3.
 */

public class CustomerSortAdapter extends BaseAdapter {

    private List<DeptPersonnelTree.Children> children;

    private Context context;

    public CustomerSortAdapter(Context context,List<DeptPersonnelTree.Children> children)
    {
        this.context = context;
        this.children = children;
    }

    @Override
    public int getCount() {
        return children==null?0:children.size();
    }

    @Override
    public Object getItem(int i) {
        return children.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final Holder holder;

        if(convertView != null) {
            view = convertView;
            holder = (Holder) view.getTag();
        }
        else {
            view = View.inflate(context, R.layout.item_customer_sort, null);
            holder = new Holder();
            holder.tv_initial = view.findViewById(R.id.tv_initial);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.iv_head = view.findViewById(R.id.iv_head);
            holder.iv_check = view.findViewById(R.id.iv_check);
            holder.tv_info = view.findViewById(R.id.tv_info);
            view.setTag(holder);
        }
        if(isFirstShow(position))
        {
            holder.tv_initial.setText(children.get(position).initial);
            holder.tv_initial.setVisibility(View.VISIBLE);
        }
        else
            holder.tv_initial.setVisibility(View.GONE);

        holder.tv_name.setText(children.get(position).name);

        if(children.get(position).parent!=null)
            holder.tv_info.setText(children.get(position).parent.name);

        if(children.get(position).ischeck)
            holder.iv_check.setImageResource(R.drawable.ic_select);
        else
            holder.iv_check.setImageResource(R.drawable.ic_notselect);


        holder.iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(children.get(position).ischeck) //取消选中操作
                    holder.iv_check.setImageResource(R.drawable.ic_notselect);
                else
                    holder.iv_check.setImageResource(R.drawable.ic_select);//选中操作

                setChecked(children.get(position),!children.get(position).ischeck);

            }
        });

        holder.iv_head.setImageResource(RandomImage.getFruitImage());
        //x.image().bind(holder.iv_head, children.get(position).photosUrls, new ImageOptions.Builder().setCircular(true).setFailureDrawableId(R.drawable.ic_common_protrait).build());

        return  view;

    }


    private boolean isFirstShow(int position)
    {
        if(position==0)
            return true;
        else
        {
            if(children.get(position).initial.equals(children.get(position-1).initial))
                return false;
            else
                return true;
        }
    }

    private  class Holder
    {
        TextView tv_initial,tv_name,tv_info;
        ImageView iv_head,iv_check;
    }

    protected  void setChecked(final DeptPersonnelTree.Children node, boolean checked)
    {
        node.ischeck = checked;
        setChildChecked(node, checked);
        if(node.parent!=null)
        {
            setNodeParentChecked(node.parent,checked);
        }
        //notifyDataSetChanged();
    }

    public void setChildChecked(DeptPersonnelTree.Children node,boolean checked)
    {
        if(!node.isLeaf())
        {
            node.ischeck = checked;
            for(DeptPersonnelTree.Children  childrenNode : node.children)
            {
                setChildChecked(childrenNode,checked);
            }
        }else
        {
            node.ischeck = checked;
        }
    }


    private void setNodeParentChecked(DeptPersonnelTree.Children children, boolean checked) {

        if(checked){
            children.ischeck = checked;
            if(children.parent!=null){
                setNodeParentChecked(children.parent,checked);
            }
        }else{
            List<DeptPersonnelTree.Children> childrenList = children.children;
            boolean isCheck = false;
            for(DeptPersonnelTree.Children children1:childrenList){
                if(children1.ischeck)
                    isCheck = true;
            }
            if(!isCheck){
                children.ischeck = checked;
            }
            if(children.parent!= null){
                setNodeParentChecked(children.parent,checked);
            }
        }
    }
}
