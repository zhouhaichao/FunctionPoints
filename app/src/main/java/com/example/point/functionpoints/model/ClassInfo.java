package com.example.point.functionpoints.model;

/**
 * Created by bobo on 2018/6/26.
 */

public class ClassInfo {

    public String title ;
    public String content;
    public Class intoClass;

    public ClassInfo(String title,String content,Class intoClass)
    {
        this.title = title;
        this.intoClass = intoClass;
        this.content = content;
    }

}
