package com.example.point.functionpoints.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.adapter.SimpleTreeAdapter;
import com.example.point.functionpoints.model.Node;

import java.util.ArrayList;

/**
 * Created by 42822 on 2018/6/12.
 */

public class ListTreeActivity extends Activity {

    private ListView lv_tree;
    private SimpleTreeAdapter treeAdapter;

    ArrayList<Node> nodes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tree);

        initData();

        initView();
    }

    private void initData()
    {
        nodes = new ArrayList<>();

            Node node0 = new Node();
            node0.name = "节点0";

                Node node00 = new Node();
                node00.name = "节点00";
                node00.level = 1;
                node00.parent = node0;
                node00.nodes =new ArrayList<>();

                    Node node000 = new Node();
                    node000.name = "节点000";
                    node000.level = 2;
                    node000.parent = node00;
                    node000.isLeaf =true;

                node00.nodes.add(node000);

                Node node01 = new Node();
                node01.name = "节点01";
                node01.level = 1;
                node01.parent = node0;
                node01.isLeaf =true;

                Node node02 = new Node();
                node02.name = "节点02";
                node02.level = 1;
                node02.parent = node0;
                node02.isLeaf =true;

            node0.nodes =new ArrayList<>();
            node0.nodes.add(node00);
            node0.nodes.add(node01);
            node0.nodes.add(node02);


            Node node1 = new Node();
            node1.name = "节点1";

                Node node10 = new Node();
                node10.name = "节点10";
                node10.level = 1;
                node10.parent = node1;

            node1.nodes =new ArrayList<>();
            node1.nodes.add(node10);


            Node node2 = new Node();
            node2.name = "节点2";

                Node node20 = new Node();
                node20.name = "节点20";
                node20.level = 1;
                node20.parent = node2;

            node2.nodes =new ArrayList<>();
            node2.nodes.add(node20);


        nodes.add(node0);
        nodes.add(node1);
        nodes.add(node2);

    }

    public void initView(){
        lv_tree = findViewById(R.id.lv_tree);

        treeAdapter = new SimpleTreeAdapter(ListTreeActivity.this,lv_tree,nodes);

        lv_tree.setAdapter(treeAdapter);
    }

}
