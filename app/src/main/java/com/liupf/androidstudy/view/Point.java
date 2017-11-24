package com.liupf.androidstudy.view;


/**
 * Created by Administrator on 2017/11/22.
 * 这个类非常简单，只定义了两个点，以及get方法和构造方法，接下来请看PointEvaluator
 */

public class Point {

    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }

}
