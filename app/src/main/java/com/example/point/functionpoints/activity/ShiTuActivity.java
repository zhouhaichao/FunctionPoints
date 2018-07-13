package com.example.point.functionpoints.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.shitu.DialogUIActivity;
import com.example.point.functionpoints.activity.shitu.PackRecycleWithSwipeActivity;
import com.example.point.functionpoints.activity.shitu.PersonalCenterActivity;
import com.example.point.functionpoints.activity.shitu.RecycleWithSwipeActivity;
import com.example.point.functionpoints.activity.shitu.SelectContactsActivity;
import com.example.point.functionpoints.activity.shitu.WeekCalendarActivity;
import com.example.point.functionpoints.adapter.CommonRecycleAdapter;
import com.example.point.functionpoints.adapter.ViewHolder;
import com.example.point.functionpoints.model.ClassInfo;
import com.example.point.functionpoints.view.RecycleViewDivider;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2018/6/29.
 */

public class ShiTuActivity extends Activity{

    private RecyclerView RV_kaungjia;
    private CommonRecycleAdapter<ClassInfo> commonRecycleAdapter;

    private List<ClassInfo> strlist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuangjia);
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.white));
        initView();
        initdata();
        initEvent();
    }

    public void initView()
    {
        RV_kaungjia = findViewById(R.id.RV_kaungjia);
    }

    public void initdata()
    {
        strlist = new ArrayList<>();
        strlist.add(new ClassInfo("部门人员选择","首字母排序结构选择，树形结构选择", SelectContactsActivity.class));
        strlist.add(new ClassInfo("个人中心","条目排版--LineShowCommonView", PersonalCenterActivity.class));
        strlist.add(new ClassInfo("弹框样式","Dialog", DialogUIActivity.class));
        strlist.add(new ClassInfo("侧滑--刷新列表","侧滑刷新列表--RecycleWithSwipeActivity", RecycleWithSwipeActivity.class));
        strlist.add(new ClassInfo("封装--侧滑--刷新列表","封装的侧滑刷新列表--PackRecycleWithSwipeActivity", PackRecycleWithSwipeActivity.class));
        strlist.add(new ClassInfo("周历","WeekCalendarActivity", WeekCalendarActivity.class));


        commonRecycleAdapter = new CommonRecycleAdapter<ClassInfo>(this,strlist,R.layout.item_kuangjia_info) {
            @Override
            public void convert(ViewHolder helper, final ClassInfo item, int position) {
                helper.setText(R.id.TV_title,item.title);
                helper.setText(R.id.TV_content,item.content);
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShiTuActivity.this,item.intoClass);
                        startActivity(intent);
                    }
                });
            }
        };

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RV_kaungjia.setLayoutManager(layoutManager);

        RV_kaungjia.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 10, 0));

        RV_kaungjia.setAdapter(commonRecycleAdapter);
    }

    public void initEvent()
    {
    }
}
