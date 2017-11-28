package com.liupf.androidstudy.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.view.MyAnimView;
import com.liupf.androidstudy.view.Point;
import com.liupf.androidstudy.view.PointEvaluator;

/**
 *
 * lpf
 * 属性动画
 * 属性动画最核心的类是ValueAnimator这个类，以及继承它的ObjectAnimator
 * 如果需要动画组合起来，需要用到AnimatorSet调用play（）这个方法
 * 可以添加监听  addListener addUpdateListener   onAnimatorLister  onAnimatorListenerAdapter
 * 如果使用xml的方式进行属性的动画的编写，需要在res路径下创建一个animator文件夹 animator 对应java代码中的ValueAnimator   objectAnimator 对应Java代码中的ObjectAnimator
 * set对应AnimatorSet
 *
 */
public class AttributeAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView tv_content;
    private MyAnimView mav;
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
        mav=(MyAnimView)findViewById(R.id.mav);
        mav.setOnClickListener(this);
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

//        //*****************************通过xml方式添加属性动画***************************
//        Animator animator= AnimatorInflater.loadAnimator(this,R.animator.sundy);
//        animator.setTarget(tv_content);
//        animator.start();

        //以上演示的均是属性动画的基础用法，下面学习TypeEvaluator,这个类告诉动画系统如何从初始值到结束值的
        //ValueAnimator.ofFloat()方法是实现了初始值和结束值之间的平滑过度，那么这个平滑过度是怎么做到的？
        //其实是系统内置了一个FloatEvaluator

//        Point point1 = new Point(0, 0);
//        Point point2 = new Point(300, 300);
//        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
//        anim.setDuration(5000);
//        anim.start();

          mav.startAnimator(true);

    }
}
