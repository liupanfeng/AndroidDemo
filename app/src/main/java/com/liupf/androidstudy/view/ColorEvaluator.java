package com.liupf.androidstudy.view;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2017/11/27.
 * 颜色变化计算器
 */

public class ColorEvaluator implements TypeEvaluator {

    /*当前红*/
    private int mCurrentRed;
    /*当前绿*/
    private int mCurrentGreen;
    /*当前蓝*/
    private int mCurrentBlue;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor= (String) startValue;
        String endColor= (String) endValue;
        int startRed=Integer.parseInt(startColor.substring(1,3),16);//按照16进制进行转换
        int startGreen=Integer.parseInt(startColor.substring(3,5),16);
        int startBlue=Integer.parseInt(startColor.substring(5,7),16);

        int endRed=Integer.parseInt(endColor.substring(1,3),16);//按照16进制进行转换
        int endGreen=Integer.parseInt(endColor.substring(3,5),16);
        int endBlue=Integer.parseInt(endColor.substring(5,7),16);

        //初始化颜色值
        if (mCurrentRed==-1){
            mCurrentRed=startRed;
        }

        if (mCurrentGreen==-1){
            mCurrentGreen=startGreen;
        }

        if (mCurrentBlue==-1){
            mCurrentBlue=startBlue;
        }

        //计算初始颜色和结束颜色之间的差值

        int diffRed=Math.abs(startRed-endRed);
        int diffGreen=Math.abs(startGreen-endGreen);
        int diffBlue=Math.abs(startBlue-endBlue);

        int colorDiff=diffRed+diffGreen+diffBlue;
        if (mCurrentRed!=endRed){
            mCurrentRed=getCurrentColor(startRed,endRed,colorDiff,0,fraction);
        }else if (mCurrentGreen!=endGreen){
            mCurrentGreen=getCurrentColor(startGreen,endGreen,colorDiff,diffRed,fraction);
        }else if (mCurrentBlue!=endBlue){
            mCurrentBlue=getCurrentColor(startBlue,endBlue,colorDiff,diffGreen+diffRed,fraction);
        }
        //将当前计算的颜色值组装起来返回
        String currentColor="#"+getHexString(mCurrentRed)+getHexString(mCurrentGreen)+getHexString(mCurrentBlue);
        return currentColor;
    }

    private int getCurrentColor(int startColor,int endColor,int colorDiff,int offset,float fraction){
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
