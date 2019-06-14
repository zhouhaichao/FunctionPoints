package com.example.point.functionpoints.activity.jichu.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.activity.common.CommonWebActivity;
import com.example.point.functionpoints.adapter.CommonRecycleAdapter;
import com.example.point.functionpoints.adapter.ViewHolder;
import com.example.point.functionpoints.model.WebInfo;
import com.example.point.functionpoints.model.f_const.ConstCommon;
import com.example.point.functionpoints.util.RandomImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiChao on 2019/5/24.
 */
public class ContentProviderActivity extends TitleActivity {

    @BindView(R.id.rv_weburl)
    RecyclerView rv_weburl;

    CommonRecycleAdapter<WebInfo> adapter;
    ArrayList<WebInfo> webInfoList ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        //在Activity 类中绑定 ：ButterKnife.bind(this);必须在setContentView();之后绑定；且父类bind绑定后，子类不需要再bind
        //在非Activity 类（eg：Fragment、ViewHold）中绑定： ButterKnife.bind(this，view);这里的this不能替换成getActivity（）。
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initView(){
        setTitleText("ButterKnife-黄油刀注解插件");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_weburl.setLayoutManager(layoutManager);

        webInfoList = new ArrayList<>();
        webInfoList.add(new WebInfo("简书","Android：关于ContentProvider的知识都在这里了","https://www.jianshu.com/p/ea8bc4aaf057"));
//        webInfoList.add(new WebInfo("简书","Android Butterknife使用方法总结","https://www.jianshu.com/p/3678aafdabc7"));
//        webInfoList.add(new WebInfo("简书","ButterKnife,你真的了解吗？","https://www.jianshu.com/p/2967ff971177"));
//        webInfoList.add(new WebInfo("简书","编译时注解 - ButterKnife源码分析和手写","https://www.jianshu.com/p/fa29253a1579"));



        adapter = new CommonRecycleAdapter<WebInfo>(this,webInfoList,R.layout.item_common_card) {
            @Override
            public void convert(ViewHolder helper, final WebInfo item, int position) {
                helper.setText(R.id.tv_title,item.title);
                helper.setText(R.id.tv_content,item.introduce);
                helper.getView(R.id.ll_bg).setBackgroundResource(RandomImage.getGradientBG(position));

                helper.getView(R.id.ll_bg).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(item.url.startsWith("http")){
                            Intent intent = new Intent(ContentProviderActivity.this, CommonWebActivity.class);
                            intent.putExtra(ConstCommon.INTENT_EXTRA_TITLE,item.title);
                            intent.putExtra(ConstCommon.INTENT_EXTRA_URL,item.url);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"web url地址 错误！",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //helper.setText(R.id.);
            }
        };
        rv_weburl.setAdapter(adapter);
    }


    private void initData(){
        // 设置URI
        Uri uri_user = Uri.parse("content://cn.zhou.myprovider/user");

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 3);
        values.put("name", "Iverson");


        // 获取ContentResolver
        ContentResolver resolver =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user,values);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"_id","name"}, null, null, null);
        while (cursor.moveToNext()){
            Log.d("ool","query book:" + cursor.getInt(0) +" "+ cursor.getString(1));

            // 将表中数据全部输出
        }
        cursor.close();
        // 关闭游标

        /**
         * 对job表进行操作
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_job = Uri.parse("content://cn.zhou.myprovider/job");

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 3);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver2.insert(uri_job,values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id","job"}, null, null, null);
        while (cursor2.moveToNext()){
            Log.d("ool","query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));
            // 将表中数据全部输出
        }
        cursor2.close();
        // 关闭游标

    }
}
