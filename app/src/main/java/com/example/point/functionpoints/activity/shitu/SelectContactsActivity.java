package com.example.point.functionpoints.activity.shitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.adapter.CommonFragmentController;
import com.example.point.functionpoints.fragment.ContactsOrganizationFragment;
import com.example.point.functionpoints.fragment.ContactsSortFragment;
import com.example.point.functionpoints.model.DeptPersonnelTree;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2018/6/29.
 */

public class SelectContactsActivity extends FragmentActivity implements View.OnClickListener{

    private final  int CONTACTS_SORT = 0;//人员排序 fragment位置
    private final  int ORGANIZATION_SORT =1;//组织结构排序 fragment位置


    public  final static int DEPARTMENT = 0;//部门
    public  final static int CUSTOMER = 1;//用户

    private ArrayList<DeptPersonnelTree.Children> children;


    private CommonFragmentController fragmentPagerAdapter;

    private List<Fragment> fragmentList;

    private ViewPager vp_show_contacts;
    private TextView tv_contacts,tv_line1,tv_organization,tv_line2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
        initView();
        initEvent();
        initData();
    }

    private void initView()
    {
        vp_show_contacts = findViewById(R.id.vp_show_contacts);
        tv_contacts = findViewById(R.id.tv_contacts);
        tv_line1 = findViewById(R.id.tv_line1);
        tv_organization = findViewById(R.id.tv_organization);
        tv_line2 = findViewById(R.id.tv_line2);
    }

    private  void initEvent()
    {
        tv_contacts.setOnClickListener(this);
        tv_organization.setOnClickListener(this);
    }

    private void  initData()
    {
        fragmentList = new ArrayList<>();

        ContactsOrganizationFragment contactsOrganizationFragment = new ContactsOrganizationFragment();
        ContactsSortFragment contactsSortFragment = new ContactsSortFragment();
        fragmentList.add(contactsSortFragment);//人员排序 fragment位置　CONTACTS_SORT = 0
        fragmentList.add(contactsOrganizationFragment);//组织结构排序 fragment位置　ORGANIZATION_SORT =1

        fragmentPagerAdapter = new CommonFragmentController(getSupportFragmentManager(),fragmentList);

        vp_show_contacts.setAdapter(fragmentPagerAdapter);

        vp_show_contacts.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    ((ContactsSortFragment)fragmentPagerAdapter.getItem(CONTACTS_SORT)).refreshView();
                    statusShow(CONTACTS_SORT);
                }

                else if(position==1)
                {
                    ((ContactsOrganizationFragment)fragmentPagerAdapter.getItem(ORGANIZATION_SORT)).refreshView();
                    statusShow(ORGANIZATION_SORT);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        statusShow(CONTACTS_SORT);//默认选择　按人员排序fragment

        getPersonnelTree();//获取人员树结构数据
    }

    private void statusShow(int sortMode)
    {
        switch (sortMode)
        {
            case CONTACTS_SORT://按人员排序 导航栏状态
                tv_line1.setVisibility(View.VISIBLE);
                tv_line2.setVisibility(View.INVISIBLE);
                tv_contacts.setTextColor(getResources().getColor(R.color.select));
                tv_organization.setTextColor(getResources().getColor(R.color.no_select));
                break;

            case ORGANIZATION_SORT://按组织结构排序 导航栏状态
                tv_line2.setVisibility(View.VISIBLE);
                tv_line1.setVisibility(View.INVISIBLE);
                tv_organization.setTextColor(getResources().getColor(R.color.select));
                tv_contacts.setTextColor(getResources().getColor(R.color.no_select));
                break;
        }
    }


    private void  getPersonnelTree()
    {
       //result为模拟网络请求返回的json字符串

        String result = "{'rc':0,'children':[{'OriginalCode':null,'type':0,'id':'1','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'研发测试','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'38','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'总经办','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'225','photosUrls':'http://sungu.8800.org:84/Controls/UploadFile/app/b42f5d048ba64ea188f7edee58ca00c7.jpg','text':null,'pid':0,'itemType':1,'name':'管理员','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'33','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'销售部','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'226','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'业务员A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'227','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'业务员B','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'39','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'直销','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'239','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'直销A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':true,'childOuter':false}],'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'34','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'工程部','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'228','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'项目经理A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'229','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'项目经理B','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'230','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'项目监理C','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'35','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'仓管部','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'233','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'仓管员A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'36','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'财务部','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'231','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'财务A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'37','photosUrls':null,'text':null,'pid':0,'itemType':0,'name':'售后部','checked':false,'chkDisabled':false,'halfCheck':false,'children':[{'OriginalCode':null,'type':0,'id':'232','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'售后师傅A','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false},{'OriginalCode':null,'type':0,'id':'234','photosUrls':'','text':null,'pid':0,'itemType':1,'name':'售后张师傅12345','checked':false,'chkDisabled':false,'halfCheck':false,'children':null,'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false}],'nocheck':false,'childOuter':false}]}";
        Gson gson = new Gson();
        DeptPersonnelTree deptPersonnelTree = gson.fromJson(result,DeptPersonnelTree.class);
        if(deptPersonnelTree.rc==0)
        {
            if(deptPersonnelTree.children!=null)
            {
                //如果父节点只有一个 就默认展开
                if(deptPersonnelTree.children.size()==1)
                    deptPersonnelTree.children.get(0).setExpand(true);

                //完善补充节点数据
                for(int i = 0 ;i<deptPersonnelTree.children.size();i++)
                {
                    deptPersonnelTree.children.get(i).children = improveData(deptPersonnelTree.children.get(i));
                }
                //设置默认选中的用户
                /*if(lstPortraitInfo!=null)
                {
                    for(int i = 0 ;i< lstPortraitInfo.size();i++)
                    {
                        if(setCheckedforId(deptPersonnelTree.children,lstPortraitInfo.get(i).id,true))
                            Log.i("DDZ","添加默认选中--"+lstPortraitInfo.get(i).name);
                    }
                }*/
                children = deptPersonnelTree.children;//全局变量赋值

                //向按renyuan排列的frgment传送数据
                ((ContactsSortFragment)fragmentPagerAdapter.getItem(CONTACTS_SORT)).setChildren(deptPersonnelTree.children);
                //向按组织架构排列的frgment传送数据
                ((ContactsOrganizationFragment)fragmentPagerAdapter.getItem(ORGANIZATION_SORT)).setChildren(deptPersonnelTree.children);
                Log.i("DDZ","添加默认选中--");
            }
        }

    }

    //完善视图节点数据
    public ArrayList<DeptPersonnelTree.Children> improveData(DeptPersonnelTree.Children children){
        if(children.itemType == DEPARTMENT&&children.children!=null )
        {
            for(int i = 0;i<children.children.size();i++)
            {
                children.children.get(i).level = children.level+1;
                children.children.get(i).parent = children;

                children.children.get(i).children = improveData(children.children.get(i));

                if(children.children.get(i).itemType == CUSTOMER )
                    children.children.get(i).leafCount=1;
                children.leafCount += children.children.get(i).leafCount;
            }
        }
        return children.children;
    }


    //根据id 设置选中状态
    public boolean setCheckedforId(ArrayList<DeptPersonnelTree.Children> childrens ,Long id , boolean checked)
    {
        if(childrens != null)
        {
            for(int i = 0 ;i < childrens.size();i++)
            {
                if(childrens.get(i).itemType == CUSTOMER && childrens.get(i).id.equals(id))
                {
                    childrens.get(i).setChecked(checked);
                    return true;
                }
                else
                {
                    if(setCheckedforId(childrens.get(i).children,id,checked))
                        return true;
                }
            }
        }
        return false;
    }


    //region 获取所有选中的人员
    private ArrayList<DeptPersonnelTree.Children> getCheckedChildren(ArrayList<DeptPersonnelTree.Children> childrens)
    {
        ArrayList<DeptPersonnelTree.Children> returnData = new ArrayList<>();
        if(childrens!=null)
        {
            for(int i = 0 ;i<childrens.size();i++)
            {
                if(childrens.get(i).itemType==CUSTOMER )
                {
                    if(childrens.get(i).ischeck)
                        returnData.add(childrens.get(i));
                }
                else
                    returnData.addAll(getCheckedChildren(childrens.get(i).children));
            }
        }
        return returnData;
    }
    //endregion


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_contacts://按人员排列
                vp_show_contacts.setCurrentItem(CONTACTS_SORT);
                break;

            case R.id.tv_organization://按组织结构排列
                vp_show_contacts.setCurrentItem(ORGANIZATION_SORT);
                break;
        }
    }

}
