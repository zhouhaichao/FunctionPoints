package com.example.point.functionpoints.view.barrage.model;

/**
 * 数据源接口
 *
 * Created by wangjie on 2019/3/7.
 */

public interface DataSource {
    // 返回当前的类型
    int getType();
    // 返回生成的时间
    long getShowTime();
}
