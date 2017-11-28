package com.liupf.androidstudy.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
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

    private boolean isNeedStart;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color){
        this.color=color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

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

    public void startAnimator(boolean start){
        isNeedStart=start;
        invalidate();
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(currentPoint==null){
            currentPoint=new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }
    }


    /**
     * @param canvas
     * 绘制一个圆
     */
    private void drawCircle(Canvas canvas){
        float x=currentPoint.getX();
        float y=currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void startAnimation(){
        if (!isNeedStart)return;
        Point startPoint=new Point(RADIUS,RADIUS);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint= (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator objectAnimator=ObjectAnimator.ofObject(this,"color",new ColorEvaluator(), "#00FF00", "#FF0000");
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

}
