package com.example.point.functionpoints.model;

import java.util.List;

/**
 * Created by HaiChao on 2018/12/20.
 */
public class GsonFormatInfo {

    /**
     * rc : 0
     * columns : [{"key":"houseType","lable":"客户类型","promt":"请选择客户类型","type":"option","code":"526"},{"key":"area","lable":"施工面积","promt":"请输入施工面积","type":"text","code":""},{"key":"defString1","lable":"背景","promt":"请输入客户背景","type":"text","code":""},{"key":"defString2","lable":"预计金额","promt":"请输入预计金额","type":"text","code":""},{"key":"defString3","lable":"竞争对手","promt":"请输入竞争对手","type":"text","code":""},{"key":"defTime1","lable":"预计废单时间","promt":"请选择预计废单时间","type":"date","code":""},{"key":"defImage1","lable":"客户图片","promt":"请选择图片","type":"images","code":""}]
     */

    private int rc;
    private List<ColumnsBean> columns;

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public List<ColumnsBean> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnsBean> columns) {
        this.columns = columns;
    }

    public static class ColumnsBean {
        /**
         * key : houseType
         * lable : 客户类型
         * promt : 请选择客户类型
         * type : option
         * code : 526
         */

        private String key;
        private String lable;
        private String promt;
        private String type;
        private String code;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public String getPromt() {
            return promt;
        }

        public void setPromt(String promt) {
            this.promt = promt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
