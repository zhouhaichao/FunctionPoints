package com.example.point.functionpoints.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.jichu.provider.ContentProviderActivity;
import com.example.point.functionpoints.adapter.CommonRecycleAdapter;
import com.example.point.functionpoints.adapter.ViewHolder;
import com.example.point.functionpoints.model.ClassInfo;
import com.example.point.functionpoints.view.RecycleViewDivider;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaiChao on 2019/5/24.
 */
public class JiChuActivity extends Activity {


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
        strlist.add(new ClassInfo("ContentProvider","利用retrofit Rxjava Okhttp框架", ContentProviderActivity.class));
//        strlist.add(new ClassInfo("PICASSO","利用picasso图片框架", PicassoActivity.class));
//        strlist.add(new ClassInfo("本地文件选择","选择本地文件，设置各种样式", FilePickActivity.class));
//        strlist.add(new ClassInfo("LeakCanary框架","内存优化，针对内存泄漏总结，内存泄露的根本原因：长生命周期的对象持有短生命周期的对象。短周期对象就无法及时释放。", LeakCanaryActivity.class));
//        strlist.add(new ClassInfo("Butterknife黄油刀","ButterKnife是一个专注于Android系统的View注入框架,以前总是要写很多findViewById来找到View对象，有了ButterKnife可以很轻松的省去这些步骤。", ButterKnifeActivity.class));
//        strlist.add(new ClassInfo("pt适配界面","1. 无侵入性 2. 灵活性高 3. 不会影响系统 View 和三方 View 的大小 4. 不会失效", PtShiPeiActivity.class));


        commonRecycleAdapter = new CommonRecycleAdapter<ClassInfo>(this,strlist,R.layout.item_kuangjia_info) {
            @Override
            public void convert(ViewHolder helper, final ClassInfo item, int position) {
                helper.setText(R.id.TV_title,item.title);
                helper.setText(R.id.TV_content,item.content);
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(JiChuActivity.this,item.intoClass);
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
