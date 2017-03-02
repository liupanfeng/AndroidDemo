package com.liupf.androidstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.animation.AnimationUtil;

public class AttributeAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ImageView ivGirl;
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
        ivGirl=(ImageView)findViewById(R.id.iv_girl);
    }

    @Override
    public void onClick(View v) {
        type++;
        if (type%2==0){
            AnimationUtil.callDialAnimator(ivGirl,View.VISIBLE);
        }else{
            AnimationUtil.callDialAnimator(ivGirl,View.GONE);
        }
    }
}
