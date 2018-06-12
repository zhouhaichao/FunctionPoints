package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.point.functionpoints.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 42822 on 2018/6/12.
 */

public abstract class TreeListViewAdapter extends BaseAdapter {

    public Context context;
    public ListView listView;
    public ArrayList<Node> nodes;//原始树形结构nodes

    public ArrayList<Node> allNodes;//按顺序转化为list

    public ArrayList<Node> showNodes;//需要展示的nodes列表

    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }
    public void setOnTreeNodeClickListener(
            OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeListViewAdapter(Context context, ListView listView, ArrayList<Node> nodes)
    {
        this.context =context;
        this.listView =listView;
        this.nodes =nodes;
        initData();
        initView();
    }

    public void initData()
    {
        allNodes = getSortedNodes(this.nodes);

        showNodes = getVisibleNode(allNodes);
    }

    private void initView()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                expandOrCollapse(position);

                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(nodes.get(position),position);
                }
            }
        });
    }

    protected  void setChecked(final Node node, boolean checked)
    {
        node.isCheck = checked;
        setChildChecked(node, checked);
        if(node.parent!=null)
        {
            setNodeParentChecked(node.parent,checked);
        }
        notifyDataSetChanged();
    }

    public void setChildChecked(Node node,boolean checked)
    {
        if(!node.isLeaf())
        {
            node.isCheck = checked;
            for(Node childrenNode : node.nodes)
            {
                setChildChecked(childrenNode,checked);
            }
        }else
        {
            node.isCheck = checked;
        }
    }


    public void setNodeParentChecked(Node node,boolean checked) {
        if (checked) {
            node.isCheck = checked;
            if (node.parent != null) setNodeParentChecked(node.parent, checked);
        } else {
            List<Node> childrens = node.nodes;
            boolean isChecked = false;
            for (Node children : childrens) {
                if (children.isCheck) {
                    isChecked = true;
                }
            } //如果所有自节点都没有被选中 父节点也不选中
            if (!isChecked) {
                node.isCheck = checked;

            }
            if (node.parent != null) setNodeParentChecked(node.parent, checked);
        }
    }



    public void expandOrCollapse(int position) {
        Node n = showNodes.get(position);
        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpend);

                showNodes = getVisibleNode(allNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }

    }

    @Override
    public int getCount() {
        return showNodes.size();
    }

    @Override
    public Object getItem(int i) {
        return showNodes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = showNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        // 设置内边距
        convertView.setPadding(node.level * 40, 3, 3, 3);
        return convertView;
    }


    public ArrayList<Node> getSortedNodes(ArrayList<Node> nodes)
    {
        ArrayList<Node> retrunData =new ArrayList<>();
        if(nodes!=null)
        {
            for(int i = 0 ;i<nodes.size();i++){
                retrunData.add(nodes.get(i));
                retrunData.addAll(getSortedNodes(nodes.get(i).nodes));
            }
        }
        return retrunData;
    }


    public ArrayList<Node> getVisibleNode(ArrayList<Node> nodes)
    {
        ArrayList<Node> result = new ArrayList<Node>();

        for(int i = 0;i<nodes.size();i++)
        {
            if(nodes.get(i).level==0||nodes.get(i).parent.isExpend)
            {
                result.add(nodes.get(i));
            }
        }
        return result;
    }


    public abstract View getConvertView(Node node, int position,View convertView, ViewGroup parent);
}
