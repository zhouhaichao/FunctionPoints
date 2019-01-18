package com.example.point.functionpoints.activity.kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
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
 * Created by HaiChao on 2019/1/10.
 */
public class ButterKnifeActivity extends TitleActivity {

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
    }

    private void initView(){
        setTitleText("ButterKnife-黄油刀注解插件");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_weburl.setLayoutManager(layoutManager);

        webInfoList = new ArrayList<>();
        webInfoList.add(new WebInfo("CSDN","ButterKnife入门教程","https://blog.csdn.net/u012527802/article/details/81059568"));
        webInfoList.add(new WebInfo("简书","Android Butterknife使用方法总结","https://www.jianshu.com/p/3678aafdabc7"));
        webInfoList.add(new WebInfo("简书","ButterKnife,你真的了解吗？","https://www.jianshu.com/p/2967ff971177"));
        webInfoList.add(new WebInfo("简书","编译时注解 - ButterKnife源码分析和手写","https://www.jianshu.com/p/fa29253a1579"));
        webInfoList.add(new WebInfo("ButterKnife","AAAAAAAAAAAAAAAAAAAAAAAA","url"));
        webInfoList.add(new WebInfo("ButterKnife","AAAAAAAAAAAAAAAAAAAAAAAA","url"));
        webInfoList.add(new WebInfo("ButterKnife","AAAAAAAAAAAAAAAAAAAAAAAA","url"));
        webInfoList.add(new WebInfo("ButterKnife","AAAAAAAAAAAAAAAAAAAAAAAA","url"));
        webInfoList.add(new WebInfo("ButterKnife","AAAAAAAAAAAAAAAAAAAAAAAA","url"));


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
                            Intent intent = new Intent(ButterKnifeActivity.this, CommonWebActivity.class);
                            intent.putExtra(ConstCommon.INTENT_EXTRA_TITLE,item.title);
                            intent.putExtra(ConstCommon.INTENT_EXTRA_URL,item.url);
                            startActivity(intent);
                        }else{
                            ToastUtils.showShort("web url地址 错误！");
                        }

                    }
                });
                //helper.setText(R.id.);
            }
        };
        rv_weburl.setAdapter(adapter);



    }

}
