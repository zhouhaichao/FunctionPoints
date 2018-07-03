package com.example.point.functionpoints.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.adapter.CustomerTreeAdapter;
import com.example.point.functionpoints.model.DeptPersonnelTree;

import java.util.ArrayList;

/**
 * Created by bobo on 2018/7/2.
 */

public class ContactsOrganizationFragment extends Fragment {

    private ListView lv_contact;

    private ArrayList<DeptPersonnelTree.Children> children;

    public CustomerTreeAdapter treeAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_organization, container, false);

        initView(view);

        initData();

        return view;
    }

    private void initView(View view)
    {
        lv_contact = view.findViewById(R.id.lv_contact);
    }

    public void initData()
    {
        if(children!=null)
        {
            treeAdapter = new CustomerTreeAdapter(getActivity(),lv_contact,this.children);
            lv_contact.setAdapter(treeAdapter);
            refreshView();
        }
    }

    public  void setChildren(ArrayList<DeptPersonnelTree.Children> children)
    {
        this.children = children;
        if(lv_contact!=null)
        {
            treeAdapter = new CustomerTreeAdapter(getActivity(),lv_contact,this.children);
            lv_contact.setAdapter(treeAdapter);
            refreshView();
        }
    }

    public void refreshView()
    {
        if(treeAdapter != null)
            treeAdapter.notifyDataSetChanged();
    }

}
