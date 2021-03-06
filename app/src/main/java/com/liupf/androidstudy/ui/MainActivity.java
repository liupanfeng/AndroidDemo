package com.liupf.androidstudy.ui;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;
import com.liupf.androidstudy.Adapter.MajorRecyclerAdapter;
import com.liupf.androidstudy.R;
import com.liupf.androidstudy.animation.AnimationUtil;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;
import com.liupf.androidstudy.recycler.ItemDivideDecoration;
import com.liupf.androidstudy.util.Contants;

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

    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      content= (FrameLayout) findViewById(R.id.content);
        iv=(ImageView)findViewById(R.id.iv);
        iv.setOnClickListener(this);
        iv.setSelected(true);
        addInfos();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        adapter=new MajorRecyclerAdapter(this,mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new ItemDivideDecoration(AnimationUtil.dip2px(MainActivity.this,2), adapter));
        adapter.addAll(infos);


    }

    private void addInfos() {
//        info=new ContentInfo("拨号键");
//        infos.add(info);
//        info=new ContentInfo("使用WindowManager实现悬浮窗");
//        infos.add(info);
        info=new ContentInfo("属性动画");
        infos.add(info);
//        info=new ContentInfo("MaterialDesign动画-palette");
//        infos.add(info);
//        info=new ContentInfo("透明度变化的toolbar");
//        infos.add(info);
//        info=new ContentInfo("TabLayout的简单使用");
//        infos.add(info);
//        info=new ContentInfo("MVP_LOGIN");
//        infos.add(info);
        info=new ContentInfo(Contants.NEW_FEATURES);
        infos.add(info);

        info=new ContentInfo(Contants.EXPANDABLE_LISTVIEW);
        infos.add(info);

        info=new ContentInfo(Contants.ATTRIBUTE_ANIMATION);
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

    public int[] twoSum(int[] nums, int target) {
        int[] arr={};
        for(int i=0;i<nums.length-1;i++){
            for(int j=0;i<nums.length-1;j++){
                if(nums[i]+nums[j]==target){
                    arr[0]=nums[i];
                    arr[1]=nums[j];
                    return arr;
                }
            }
        }
        return arr;
    }
}
