package com.example.point.functionpoints.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2018/6/29.
 */

public class DeptPersonnelTree {

    public int rc; //返回结果代码
    public ArrayList<Children> children;

    public class Children
    {
        //原始数据源
        public Long id ;
        public String name;
        public int itemType; //0代表部门，1代表人员
        public String photosUrls; //头像图片地址
        public ArrayList<Children> children;

        //视图展示所需要的状态
        public boolean ischeck = false;//是否选中
        public boolean isExpand = false;//是否展开
        public Children parent;//父节点
        public int level;//当前级别
        public int leafCount;//叶子--人员数量
        public String initial;//首字母

        public boolean isLeaf(){
            if(children==null||children.size()==0)
                return true;
            else
                return false;
        }


        //对于树形结构 设置伸展操作，关联父类和子类
        public void setExpand(boolean isExpand) {
            this.isExpand = isExpand;
            if (!isExpand && children!=null) {
                for (Children node : children) {
                    node.setExpand(isExpand);
                }
            }
        }


        //对于树形结构 设置选中操作，关联父类和子类的选中状态
        public  void setChecked(boolean checked)
        {
            this.ischeck = checked;
            setChildChecked(this, checked);
            if(this.parent!=null)
            {
                setNodeParentChecked(this.parent,checked);
            }
        }

        private void setChildChecked(DeptPersonnelTree.Children node,boolean checked)   {
            if(!node.isLeaf())
            {
                node.ischeck = checked;
                if(node.children !=null)
                {
                    for(DeptPersonnelTree.Children  childrenNode : node.children)
                    {
                        setChildChecked(childrenNode,checked);
                    }
                }
            }else
            {
                node.ischeck = checked;
            }
        }


        private void setNodeParentChecked(DeptPersonnelTree.Children children, boolean checked) {
            if(children!=null)
            {
                if(checked){
                    children.ischeck = checked;
                    if(children.parent!=null){
                        setNodeParentChecked(children.parent,checked);
                    }
                }else{
                    List<Children> childrenList = children.children;
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

    }
}
