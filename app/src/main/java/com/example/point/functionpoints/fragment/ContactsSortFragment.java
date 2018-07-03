package com.example.point.functionpoints.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.adapter.CustomerSortAdapter;
import com.example.point.functionpoints.model.DeptPersonnelTree;
import com.example.point.functionpoints.util.HanziToPinyin;
import com.example.point.functionpoints.view.QuickLocationBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.point.functionpoints.activity.shitu.SelectContactsActivity.CUSTOMER;

/**
 * Created by bobo on 2018/7/3.
 */

public class ContactsSortFragment extends Fragment {

    private ListView lv_contact;

    private TextView tv_word;

    private TextView tv_info;

    private QuickLocationBar qlb_word;


    private ArrayList<DeptPersonnelTree.Children> children;


    private ArrayList<String> words = new ArrayList<>();//首字母集合

    private ArrayList<DeptPersonnelTree.Children> customer;//仅包含用户的children列表

    public CustomerSortAdapter sortAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_sort, container, false);
        initView(view);
        initData();

        return view;
    }

    public void initView(View view)
    {
        lv_contact = view.findViewById(R.id.lv_contact);
        tv_word = view.findViewById(R.id.tv_word);
        tv_info = view.findViewById(R.id.tv_info);
        qlb_word = view.findViewById(R.id.qlb_word);
    }

    public void initData(){
        if(customer!=null)
        {
            sortAdapter = new CustomerSortAdapter(getActivity(),customer);
            lv_contact.setAdapter(sortAdapter);
        }
        qlb_word.setTextDialog(tv_word);

        qlb_word.setOnTouchLitterChangedListener(new QuickLocationBar.OnTouchLetterChangedListener() {
            @Override
            public void touchLetterChanged(String s) {
                if(customer!=null)
                {
                    for(int i = 0 ; i< customer.size();i++)
                    {
                        if(s.equals(customer.get(i).initial))
                        {
                            lv_contact.setSelection(i);
                            return ;
                        }
                    }
                }
            }
        });

        if(words.size()>0)
            qlb_word.setCharacters(words);
    }

    public void setChildren(ArrayList<DeptPersonnelTree.Children> children){

        this.children = children;
        this.customer = getCustomer(children);
        //进行首字母排序
        Collections.sort(this.customer, new Comparator<DeptPersonnelTree.Children>() {
            @Override
            public int compare(DeptPersonnelTree.Children t1, DeptPersonnelTree.Children t2) {
                if(t1.initial.equals(t2.initial))
                {
                    return t1.name.compareTo(t2.name);
                }
                else
                {
                    if("#".equals(t1.initial))
                    {
                        return 1;
                    }
                    else if("#".equals(t2.initial))
                    {
                        return -1;
                    }
                    return t1.initial.compareTo(t2.initial);
                }
            }
        });

        for(DeptPersonnelTree.Children ch : customer)
        {
            if(ch.initial!=null)
            {
                if(words.size()>0)
                {
                    if(!words.get(words.size()-1).equals(ch.initial))//首字母列表最后一位和新的首字母不等 针对排完序列以后的列表
                        words.add(ch.initial);
                }
                else//首次填装首字母
                    words.add(ch.initial);
            }
        }

        if(qlb_word!=null)
            qlb_word.setCharacters(words);


        if(lv_contact!=null)
        {
            sortAdapter = new CustomerSortAdapter(getActivity(),customer);
            lv_contact.setAdapter(sortAdapter);
        }

    }

    private ArrayList<DeptPersonnelTree.Children> getCustomer(ArrayList<DeptPersonnelTree.Children> children){
        if(children!= null)
        {
            ArrayList<DeptPersonnelTree.Children> returnData = new ArrayList<>();
            for(int i = 0 ; i<children.size();i++)
            {
                if(children.get(i).itemType==CUSTOMER)
                {
                    children.get(i).initial = HanziToPinyin.getLetter(children.get(i).name);//装载首字母
                    returnData.add(children.get(i));
                }
                else
                    returnData.addAll(getCustomer(children.get(i).children));
            }
            return returnData;
        }
        return null;
    }

    public void refreshView(){
        if(sortAdapter != null)
            sortAdapter.notifyDataSetChanged();
    }

}
