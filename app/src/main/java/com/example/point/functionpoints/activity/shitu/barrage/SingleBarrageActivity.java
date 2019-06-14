package com.example.point.functionpoints.activity.shitu.barrage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.activity.TitleActivity;
import com.example.point.functionpoints.model.BarrageData;
import com.example.point.functionpoints.picasso.CircleTransform;
import com.example.point.functionpoints.view.barrage.adapter.BarrageAdapter;
import com.example.point.functionpoints.view.barrage.ui.BarrageView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HaiChao on 2019/4/22.
 */
public class SingleBarrageActivity extends TitleActivity {

    private String SEED[] = {"景色还不错啊", "小姐姐真好看！，", "又去哪里玩了？我也要去！", "门票多少啊？", "厉害啦！"};
    private final int ICON_RESOURCES[] = {R.drawable.cat, R.drawable.corgi, R.drawable.lovelycat, R.drawable.boy, R.drawable.girl,R.drawable.samoyed};

    private BarrageView barrageView;
    private BarrageAdapter<BarrageData> mAdapter;

    public static void show(Context context){
        Intent intent = new Intent(context,SingleBarrageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_barrage);

        barrageView = findViewById(R.id.barrage);

        initBarrage();
    }

    private void initBarrage(){
        BarrageView.Options options = new BarrageView.Options()
                .setGravity(BarrageView.GRAVITY_TOP)                // 设置弹幕的位置
                .setInterval(50)                                     // 设置弹幕的发送间隔
                .setSpeed(200,29)                   // 设置速度和波动值
                .setModel(BarrageView.MODEL_RANDOM)     // 设置弹幕生成模式
                .setRepeat(-1)                                       // 循环播放 默认为1次 -1 为无限循环
                .setClick(false);                                    // 设置弹幕是否可以点击
        barrageView.setOptions(options);
        // 设置适配器 第一个参数是点击事件的监听器
        barrageView.setAdapter(mAdapter = new BarrageAdapter<BarrageData>(null, this) {
            @Override
            public BarrageViewHolder<BarrageData> onCreateViewHolder(View root, int type) {
                return new SingleBarrageActivity.ViewHolder(root);
            }

            @Override
            public int getItemLayout(BarrageData barrageData) {
                return R.layout.item_barrage_normal;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        initData();
    }

    private void initData() {
        int strLength = SEED.length;
        List<BarrageData> dataList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new BarrageData(SEED[i%strLength], 0,i));
        }
        mAdapter.addList(dataList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        barrageView.destroy();
    }

    class ViewHolder extends BarrageAdapter.BarrageViewHolder<BarrageData> {

        private ImageView mHeadView;
        private TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);

            mHeadView = itemView.findViewById(R.id.image);
            mContent = itemView.findViewById(R.id.content);
        }

        @Override
        protected void onBind(BarrageData data) {
            Picasso.with(SingleBarrageActivity.this).load(ICON_RESOURCES[data.getPos()%ICON_RESOURCES.length])
                    .transform(new CircleTransform())
                    .into(mHeadView);
            mContent.setText(data.getContent());
        }
    }
}

