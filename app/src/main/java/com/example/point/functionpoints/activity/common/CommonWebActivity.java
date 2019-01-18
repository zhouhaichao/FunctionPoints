package com.example.point.functionpoints.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.model.f_const.ConstCommon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiChao on 2019/1/10.
 */
public class CommonWebActivity extends TitleActivity {


    @BindView(R.id.wv_common)
    WebView wv_common;

    private String webUrl;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        ButterKnife.bind(this);

        initIntent();
        initView();

    }

    private void initIntent(){
        Intent intent = getIntent();

        String title = intent.getStringExtra(ConstCommon.INTENT_EXTRA_TITLE);
        if(title!=null){
            setTitleText(title);
        }

        webUrl = intent.getStringExtra(ConstCommon.INTENT_EXTRA_URL);


    }

    private void initView(){
        if(webUrl!=null){
            wv_common.loadUrl(webUrl);
            WebSettings webSettings = wv_common.getSettings();
            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.setJavaScriptEnabled(true);
            // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
            // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

            //支持插件
            //webSettings.setPluginsEnabled(true);

            //设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            //缩放操作
            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

            //其他细节操作
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式




        }else{
            ToastUtils.showShort("web url地址 错误！");
        }
    }
}
