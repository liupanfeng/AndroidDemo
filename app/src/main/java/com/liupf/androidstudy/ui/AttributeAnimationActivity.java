package com.liupf.androidstudy.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liupf.androidstudy.R;

public class AttributeAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView tv_content;
    int type=0;
    public static void actionAttributeAnimationActivity(Context context){
        Intent intent=new Intent(context,AttributeAnimationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_animation);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        tv_content=(TextView) findViewById(R.id.tv_content);
    }

    @Override
    public void onClick(View v) {
        //********************************透明度********************************
//        ObjectAnimator animator=ObjectAnimator.ofFloat(tv_content,"alpha",1f,0f,1f);
//        animator.setDuration(1000);
//        animator.start();

        //*********************************旋转*********************************
//        ObjectAnimator animator=ObjectAnimator.ofFloat(tv_content,"rotation",0f,360f);
//        animator.setDuration(1000);
//        animator.start();

        //******************************移动********************************************
//        float fValue=tv_content.getTranslationX();
//        ObjectAnimator animator=ObjectAnimator.ofFloat(tv_content,"translationX",fValue,-500f,fValue);
//        animator.setDuration(5000);
//        animator.start();

        //*******************************缩放*****************************************
//        ObjectAnimator animator=ObjectAnimator.ofFloat(tv_content,"scaleY",1f,3f,1f);
//        animator.setDuration(5000);
//        animator.start();

        //*****************************组合动画**************************************
        //组合动画主要借助于AnimatorSet这个类，提供的一个Play()这个方法
//        float fValue=tv_content.getTranslationX();
//        ObjectAnimator moveIn=ObjectAnimator.ofFloat(tv_content,"translationX",-500f,fValue);
//        ObjectAnimator rotation=ObjectAnimator.ofFloat(tv_content,"rotation",0f,360f);
//        ObjectAnimator fadeInOut=ObjectAnimator.ofFloat(tv_content,"alpha",1f,0f,1f);
//        ObjectAnimator scaleY=ObjectAnimator.ofFloat(tv_content,"scaleY",1f,3f,1f);
//
//        AnimatorSet animatorSet=new AnimatorSet();
//        animatorSet.play(rotation).with(fadeInOut).after(moveIn).before(scaleY);
//        animatorSet.setDuration(3000);
//        scaleY.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.d("lpf","onAnimationStart()");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.d("lpf","onAnimationEnd()");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                Log.d("lpf","onAnimationCancel");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                float f=animation.getDuration();
//                Log.d("lpf","onAnimationRepeat+Duration:"+f);
//            }
//        });
////        //添加下面的监听可以避免重载太多的方法，想使用那个就重载那个就好了
////        fadeInOut.addListener(new AnimatorListenerAdapter() {
////            @Override
////            public void onAnimationEnd(Animator animation) {
////                super.onAnimationEnd(animation);
////            }
////        });
//        animatorSet.start();

        Animator animator= AnimatorInflater.loadAnimator(this,R.animator.sundy);
        animator.setTarget(tv_content);
        animator.start();

    }
}
