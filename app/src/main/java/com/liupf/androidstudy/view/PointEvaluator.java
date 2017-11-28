package com.liupf.androidstudy.view;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2017/11/23.
 * 自定义了PointEvaluator 同样实现了TypeEvaluator接口并重写了evaluate方法
 * 方法中的逻辑也很简单，主要是将传入的两个对象强转换成开始点和结束点
 * 然后，根据fraction计算当前动画的x，y坐标 最后组装到一个新的point中并返回
 * 接下来请看MyAnimView 这个类将使用刚刚新建的Point对象绘制一个view
 */

public class PointEvaluator implements TypeEvaluator{

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint= (Point) startValue;
        Point endPoint= (Point) endValue;
        float x=startPoint.getX()+fraction*(endPoint.getX()-startPoint.getX());
        float y=startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
