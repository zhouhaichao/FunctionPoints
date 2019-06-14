package com.example.point.functionpoints.view.barrage.ui;

import android.view.View;

/**
 * 弹幕视图的接口
 *
 * Created by wangjie on 2019/3/15.
 */

public interface IBarrageView {
    // 添加视图
    void addBarrageItem(View view);
    // 获取是否存在缓存
    View getCacheView(int type);
    // 发送View间隔 默认为0
    long getInterval();
    // 循环的次数
    int getRepeat();

}
