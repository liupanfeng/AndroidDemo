package com.liupf.androidstudy;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;
import com.liupf.androidstudy.Adapter.MajorRecyclerAdapter;
import com.liupf.androidstudy.animation.AnimationUtil;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;
import com.liupf.androidstudy.recycler.ItemDivideDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout content;
    private RecyclerView mRecyclerView;
    private List<BaseInfo> infos=new ArrayList<>();
    private  ContentInfo info;
    private BaseRecyclerAdapter adapter;

    private ImageView iv;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      content= (FrameLayout) findViewById(R.id.content);
        iv=(ImageView)findViewById(R.id.iv);
        iv.setOnClickListener(this);
        iv.setSelected(true);
        addInfos();
       /* infos.add(info);
        info=new ContentInfo("解析html");*/

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        adapter=new MajorRecyclerAdapter(this,mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new ItemDivideDecoration(AnimationUtil.dip2px(MainActivity.this,2), adapter));
        adapter.addAll(infos);


    }

    private void addInfos() {
        info=new ContentInfo("拨号键");
        infos.add(info);
        info=new ContentInfo("使用WindowManager实现悬浮窗");
        infos.add(info);
        info=new ContentInfo("属性动画");
        infos.add(info);
        info=new ContentInfo("MaterialDesign动画-palette");
        infos.add(info);
    }

    @Override
    public void onClick(View v) {
//        if(type%2==0){
//            type++;
//            iv.setEnabled(false);
//        }else{
//            type++;
//            iv.setEnabled(true);
//        }
    }
}
