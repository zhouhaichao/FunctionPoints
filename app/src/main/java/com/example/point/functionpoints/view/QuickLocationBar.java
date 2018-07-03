package com.example.point.functionpoints.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.point.functionpoints.R;

import java.util.ArrayList;

/**
 * Created by bobo on 2018/7/3.
 */

public class QuickLocationBar extends View {

   /* private String characters[] = { "#", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z" }; */

    private String characters[] = {"#"};
    private int choose = -1;
    private Paint paint = new Paint();
    private OnTouchLetterChangedListener mOnTouchLetterChangedListener;
    private TextView mTextDialog;

    public QuickLocationBar(Context context) {
        super(context);
    }

    public QuickLocationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickLocationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnTouchLitterChangedListener(
            OnTouchLetterChangedListener onTouchLetterChangedListener) {
        this.mOnTouchLetterChangedListener = onTouchLetterChangedListener;
    }

    public void setTextDialog(TextView dialog) {
        this.mTextDialog = dialog;
    }

    public void setCharacters(ArrayList<String> words)
    {
        if(words != null)
        {
            this.characters = new String[words.size()];
            for(int i = 0 ; i< words.size();i++)
            {
                characters[i] = words.get(i);
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / characters.length;
        for (int i = 0; i < characters.length; i++) {
            // 对paint进行相关的参数设置
            paint.setColor(getResources().getColor(R.color.gray));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(150*(float) width/320);
            if (i == choose) {// choose变量表示当前显示的字符位置，若没有触摸则为-1
                paint.setColor(getResources().getColor(R.color.base));
                paint.setFakeBoldText(true);
            }
            // 计算字符的绘制的位置
            float xPos = width / 2 - paint.measureText(characters[i]) / 2;
            float yPos = singleHeight * i + singleHeight/2;
            // 在画布上绘制字符
            canvas.drawText(characters[i], xPos, yPos, paint);
            paint.reset();// 每次绘制完成后不要忘记重制Paint
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        int c = (int) (y / getHeight() * characters.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                choose = -1;//
                setBackgroundColor(0x0000);
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.GONE);
                }
                break;

            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:
                //setBackgroundColor(getResources().getColor(R.color.bg_653fac));
                if (choose != c) {
                    if (c >= 0 && c < characters.length) {
                        if (mOnTouchLetterChangedListener != null) {
                            mOnTouchLetterChangedListener.touchLetterChanged(characters[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(characters[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.GONE);
                }
                break;
        }
        return true;
    }

    public interface OnTouchLetterChangedListener {
        public void touchLetterChanged(String s);
    }


}
