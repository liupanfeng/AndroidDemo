package com.liupf.androidstudy.ui;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;
import com.liupf.androidstudy.Adapter.MajorRecyclerAdapter;
import com.liupf.androidstudy.R;
import com.liupf.androidstudy.animation.AnimationUtil;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;
import com.liupf.androidstudy.recycler.ItemDivideDecoration;
import com.liupf.androidstudy.util.URLUtils;

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
       /* infos.add(info);
        info=new ContentInfo("解析html");*/


        wv=(WebView)findViewById(R.id.wv);
        String url=URLUtils.urlEncoded("https://www.baidu.com/");
        wv.loadUrl(url);

        WebSettings wSet = wv.getSettings();
        wSet.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
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
        info=new ContentInfo("透明度变化的toolbar");
        infos.add(info);
        info=new ContentInfo("TabLayout的简单使用");
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
