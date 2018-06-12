package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.model.Node;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by 42822 on 2018/6/12.
 */

public class SimpleTreeAdapter extends TreeListViewAdapter {

    private Context context;


    public SimpleTreeAdapter(Context context, ListView listView, ArrayList<Node> nodes) {
        super(context, listView, nodes);
        this.context = context;
    }

    @Override
    public View getConvertView(final Node node, int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder holder;
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tree_node, null);
            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.cb_check = view.findViewById(R.id.cb_check);
            view.setTag(holder);
        } else{
            view  = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.tvName.setText(node.name);


        holder.cb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChecked(node,holder.cb_check.isChecked());
            }
        });

        holder.cb_check.setChecked(node.isCheck);



        return view;

    }

    class ViewHolder {
        TextView tvName, tvCount;
        CheckBox cb_check;
        ImageView iv;
    }
}
