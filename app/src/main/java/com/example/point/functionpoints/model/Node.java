package com.example.point.functionpoints.model;

import java.util.ArrayList;

/**
 * Created by 42822 on 2018/6/12.
 */

public class Node {

    public Node parent;
    public boolean isCheck;
    public boolean isExpend;
    public String name;

    public int level;
    public boolean isLeaf;

    public ArrayList<Node> nodes;

    public void setExpand(boolean isExpand) {
        this.isExpend = isExpand;
        if (!isExpand && nodes!=null) {
            for (Node node : nodes) {
                node.setExpand(isExpand);
            }
        }
    }

    public boolean isLeaf()
    {
        if(nodes==null||!(nodes.size()>0))
            return true;
        else
            return false;
    }


}
