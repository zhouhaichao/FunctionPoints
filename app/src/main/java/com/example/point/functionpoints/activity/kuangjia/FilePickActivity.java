package com.example.point.functionpoints.activity.kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.adapter.CommonRecycleAdapter;
import com.example.point.functionpoints.adapter.ViewHolder;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaiChao on 2018/8/14.
 */
public class FilePickActivity extends TitleActivity{

    public int REQUESTCODE_FROM_ACTIVITY =100;

    TextView tv_file_pick;

    RecyclerView rv_file_addr;

    CommonRecycleAdapter fileAddrAdapter;

    List<String> list =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_pick);

        initView();
        initEvent();
    }

    public void initView()
    {
        tv_file_pick = findViewById(R.id.tv_file_pick);
        rv_file_addr = findViewById(R.id.rv_file_addr);

        fileAddrAdapter = new CommonRecycleAdapter<String>(this,list,R.layout.item_swipe_sideslip) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_content,item);
            }
        };

        rv_file_addr.setAdapter(fileAddrAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_file_addr.setLayoutManager(layoutManager);

    }

    public void initEvent()
    {
        tv_file_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LFilePicker()
                        .withMaxNum(6)
                        .withActivity(FilePickActivity.this)
                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                list.clear();
                list.addAll(data.getStringArrayListExtra("paths"));

                fileAddrAdapter.notifyDataSetChanged();
                //Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
