package com.liupf.androidstudy.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MyAnimView extends View {

    /*圆的半径*/
    public static final float RADIUS=50f;
    /*当前的点*/
    private Point currentPoint;
    /*画笔*/
    private Paint mPaint;

    public MyAnimView(Context context) {
        super(context);

    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //设置抗锯齿
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);

    }
}
