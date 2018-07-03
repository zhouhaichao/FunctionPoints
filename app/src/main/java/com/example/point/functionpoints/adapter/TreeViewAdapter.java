package com.example.point.functionpoints.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.point.functionpoints.model.DeptPersonnelTree;
import com.example.point.functionpoints.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2018/7/3.
 */

public abstract class TreeViewAdapter extends BaseAdapter {

    protected Context context;

    protected ListView listview;

    public interface OnTreeNodeClickListener {
        void onClick(DeptPersonnelTree.Children children, int position);
    }

    private OnTreeNodeClickListener onTreeNodeClickListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    protected ArrayList<DeptPersonnelTree.Children> childrens;//原始树形结构nodes

    protected ArrayList<DeptPersonnelTree.Children> allChildrens;//按顺序转化为list

    protected ArrayList<DeptPersonnelTree.Children> showChildrens;//需要展示的nodes列表


    public TreeViewAdapter(Context context , ListView listview ,ArrayList<DeptPersonnelTree.Children> children)
    {
        this.context = context;
        this.listview =listview;
        this.childrens =children;
        initData();
    }

    private void initData()
    {
        allChildrens = getAllChildrens(childrens);

        showChildrens = getVisibleChildrens(allChildrens);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                expandOrCollapse(position);

                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(childrens.get(position),
                            position);
                }
            }
        });
    }

    @Override
    public int getCount() {
        return showChildrens.size();
    }

    @Override
    public Object getItem(int i) {
        return showChildrens==null?0:showChildrens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DeptPersonnelTree.Children children = this.showChildrens.get(position);
        convertView = getConvertView(children, position, convertView, parent);

        convertView.setPadding(children.level* DensityUtil.dip2px(context,20),3,3,3);

        return convertView;
    }




    public void expandOrCollapse(int position) {
        DeptPersonnelTree.Children children = showChildrens.get(position);
        if (children != null) {// 排除传入参数错误异常
            if (children.itemType!=1) {
                children.setExpand(!children.isExpand);
                showChildrens = getVisibleChildrens(allChildrens);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }


    protected  void setChecked(final DeptPersonnelTree.Children node, boolean checked)
    {
        node.ischeck = checked;
        setChildChecked(node, checked);
        if(node.parent!=null)
        {
            setNodeParentChecked(node.parent,checked);
        }
        notifyDataSetChanged();
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

    //获取所有的节点
    public ArrayList<DeptPersonnelTree.Children> getAllChildrens(ArrayList<DeptPersonnelTree.Children> childrens)
    {
        ArrayList<DeptPersonnelTree.Children> returnData = new ArrayList<>();
        if(childrens!=null)
        {
            for(int i = 0;i<childrens.size();i++)
            {
                returnData.add(childrens.get(i));
                returnData.addAll(getAllChildrens(childrens.get(i).children));
            }
        }
        return returnData;
    }

    //获取要展示的节点
    public  ArrayList<DeptPersonnelTree.Children> getVisibleChildrens(ArrayList<DeptPersonnelTree.Children> childrens)
    {
        ArrayList<DeptPersonnelTree.Children> returnData = new ArrayList<>();
        for(int i = 0 ;i<childrens.size();i++)
        {
            if(childrens.get(i).level == 0 || childrens.get(i).parent.isExpand)
            {
                returnData.add(childrens.get(i));
            }
        }
        return returnData;
    }

    public abstract View getConvertView(DeptPersonnelTree.Children node, int position, View convertView, ViewGroup parent);

}