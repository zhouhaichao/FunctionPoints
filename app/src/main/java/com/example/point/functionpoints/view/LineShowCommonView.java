package com.example.point.functionpoints.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.point.functionpoints.R;

/**
 * Created by bobo on 2018/7/4.
 */

public class LineShowCommonView extends FrameLayout //implements View.OnTouchListener
{
    LinearLayout ll_line;
    ImageView iv_icon;
    TextView tv_title;
    TextView tv_content;
    ImageView iv_arrow;
    View vw_lineundericon;
    View vw_lineunderinfo;

    //加载至输入框的内容
    private Object objEtContent;

    public LineShowCommonView(Context context) {
        super(context);
    }

    public LineShowCommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LineShowCommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

//    public ATACommonLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_line_show_common,
                this, true);
//        view.findViewById(R.id.iv_icon)
        ll_line = (LinearLayout) findViewById(R.id.ll_line);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_arrow = (ImageView) findViewById(R.id.iv_arrow);
        vw_lineundericon = findViewById(R.id.vw_lineundericon);
        vw_lineunderinfo = findViewById(R.id.vw_lineunderinfo);

//        ll_line.setOnTouchListener(this);
//        tv_content.setOnTouchListener(this);
//        iv_arrow.setOnTouchListener(this);

        if(!view.isInEditMode()){
            if(attrs!=null) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LineShowCommonView);
                ll_line.setBackgroundColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_line_background, 0)));
                //height暂时不处理
                iv_icon.setImageResource(a.getResourceId(R.styleable.LineShowCommonView_icon_src, 0));
                boolean showIcon = a.getBoolean(R.styleable.LineShowCommonView_icon_show,true);
                if(!showIcon) {
                    iv_icon.setVisibility(GONE);
                }
                tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.LineShowCommonView_title_size, 30));
                tv_title.setTextColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_title_color, 0)));
                tv_title.setText(a.getText(R.styleable.LineShowCommonView_title_text));
                tv_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.LineShowCommonView_content_size, 30));
                tv_content.setTextColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_content_color, 0)));
                //tv_content.setHintTextColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_content_hintcolor, 0)));
                tv_content.setHintTextColor(a.getColor(R.styleable.LineShowCommonView_content_hintcolor, Color.parseColor("#FFFFFF")));
                CharSequence hintText = a.getText(R.styleable.LineShowCommonView_content_hinttext);
                if(hintText != null && hintText.length() > 0) {
                    tv_content.setHint(hintText);
                }
                boolean showArrow = a.getBoolean(R.styleable.LineShowCommonView_arrow_show, true);
                if(!showArrow){
                    iv_arrow.setVisibility(INVISIBLE);
                } else {
                    iv_arrow.setImageResource(a.getResourceId(R.styleable.LineShowCommonView_arrow_src, 0));
                }

                if(!showIcon){
                    vw_lineundericon.setVisibility(GONE);
                } else {
                    boolean showlineundericon = a.getBoolean(R.styleable.LineShowCommonView_lineundericon_show,false);
                    if(showlineundericon) {
                        vw_lineundericon.setBackgroundColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_lineundericon_background,0)));
                    }
                }
                boolean showlineunderinfo = a.getBoolean(R.styleable.LineShowCommonView_lineunderinfo_show,true);
                if(showlineunderinfo) {
                    //vw_lineunderinfo.setBackgroundColor(getResources().getColor(a.getResourceId(R.styleable.LineShowCommonView_lineunderinfo_background,0)));
                    vw_lineunderinfo.setBackgroundColor(a.getColor(R.styleable.LineShowCommonView_lineunderinfo_background,Color.parseColor("#FFFFFF")));
                }
            }
        }
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int viewId = v.getId();
//        if(viewId == R.id.et_content){
//            Log.i("TAG", "viewId = R.id.et_content");
//        } else if(viewId == R.id.iv_arrow){
//            Log.i("TAG","viewId = R.id.iv_arrow");
//        }
//        Log.i("TAG","viewId =" + viewId + "  event=" + event.toString());
//        return true;
//    }
//
//    IATACommonLineTouchCallback iAtaCommonLineTouchCallback = null;
//    public void setATACommonLineTouchCallBack(IATACommonLineTouchCallback icallBack) {
//        this.iAtaCommonLineTouchCallback = icallBack;
//    }
//
//    public interface IATACommonLineTouchCallback {
//        public void onTouchLine();
//    }

    /*
    * 获取存储结果
    * */
    public Object getTv_Content()
    {
        if(objEtContent==null){
            return "";
        }else {
            return objEtContent;
        }
    }

    /*
    * 获取输入内容
    * */
    public String getTv_ContentForStr()
    {
        return tv_content.getText().toString();
    }

    //设置内容
    public void setTv_content(Object obj) {
        objEtContent = obj;
        if(obj!=null) {
            tv_content.setText(obj.toString());
        }

    }
    //设置内容字体颜色
    public void setTv_contentColor(int color){
        tv_content.setTextColor(color);
    }
    //设置时间格式用
    public void setTime(String time){
        tv_content.setText(time);
    }

    //设置内容
    public void setTv_title(CharSequence str) {
        tv_title.setText(str);
    }

    public String getTv_title(){
        return tv_title.getText().toString();
    }

    public void settv_EtHint(CharSequence str){
        tv_content.setHint(str);
    }
    //    //获取内容
//    public void setEt_content(String str){
//
//    }
    public void setLineHiden(){
        vw_lineunderinfo.setVisibility(GONE);
    }

}