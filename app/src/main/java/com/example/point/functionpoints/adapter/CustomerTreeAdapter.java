package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.model.DeptPersonnelTree;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.point.functionpoints.activity.shitu.SelectContactsActivity.CUSTOMER;
import static com.example.point.functionpoints.activity.shitu.SelectContactsActivity.DEPARTMENT;

/**
 * Created by bobo on 2018/7/2.
 */

public class CustomerTreeAdapter extends TreeViewAdapter {



    public CustomerTreeAdapter(Context context, ListView listview, ArrayList<DeptPersonnelTree.Children> children) {
        super(context, listview, children);
    }

    @Override
    public View getConvertView(final DeptPersonnelTree.Children node, int position, View convertView, ViewGroup parent) {
        View view;
        final DepViewHolder depHolder;
        final CusViewHolder cusHolder;

        if(node.isLeaf()&&node.itemType==CUSTOMER)//判断为叶子节点并且为用户
        {
            if (convertView == null||((int)convertView.getTag(R.id.tag_class))!=CUSTOMER) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_customer_check, null);

                cusHolder = new CusViewHolder();
                cusHolder.tvName = view.findViewById(R.id.tv_name);
                cusHolder.iv_check = view.findViewById(R.id.iv_check);
                cusHolder.iv_head = view.findViewById(R.id.iv_head);
                view.setTag(R.id.tag_class,CUSTOMER);
                view.setTag(R.id.tag_view,cusHolder);
            }else{
                view = convertView;
                cusHolder = (CusViewHolder) view.getTag(R.id.tag_view);
            }
            cusHolder.tvName.setText(node.name);

            if(node.ischeck)
                cusHolder.iv_check.setImageResource(R.drawable.ic_select);
            else
                cusHolder.iv_check.setImageResource(R.drawable.ic_notselect);

            cusHolder.iv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setChecked(node,!node.ischeck);
                }
            });
            //x.image().bind(cusHolder.iv_head, node.photosUrls, new ImageOptions.Builder().setCircular(true).setFailureDrawableId(R.drawable.ic_common_protrait).build());
        }
        else//组织结构节点
        {
            if (convertView == null||((int)convertView.getTag(R.id.tag_class))!=DEPARTMENT) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_department_check, null);
                depHolder = new DepViewHolder();
                depHolder.tvName = view.findViewById(R.id.tv_name);
                //holder.tvCount = (TextView) view.findViewById(R.id.tv_count);
                //holder.cb = (CheckBox) view.findViewById(R.id.check_box);
                depHolder.iv_check =  view.findViewById(R.id.iv_check);
                depHolder.iv_expand =  view.findViewById(R.id.iv_expand);
                view.setTag(R.id.tag_class,DEPARTMENT);
                view.setTag(R.id.tag_view,depHolder);
            } else {
                view = convertView;
                depHolder = (DepViewHolder) view.getTag(R.id.tag_view);
            }
            depHolder.tvName.setText(node.name+" ("+node.leafCount+")");

            if(node.ischeck)
                depHolder.iv_check.setImageResource(R.drawable.ic_select);
            else
                depHolder.iv_check.setImageResource(R.drawable.ic_notselect);

            depHolder.iv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setChecked(node,!node.ischeck);
                }
            });

            if(node.isExpand)
                depHolder.iv_expand.setImageResource(R.drawable.ic_delivery_down);
            else
                depHolder.iv_expand.setImageResource(R.drawable.ic_delivery_insert);
        }

        return view;
    }

    //部门view
    class DepViewHolder {
        TextView tvName, tvCount;
        CheckBox cb;
        ImageView iv_check,iv_expand;
    }

    //用户View
    class  CusViewHolder{
        TextView tvName;
        ImageView iv_check,iv_head;
    }
}