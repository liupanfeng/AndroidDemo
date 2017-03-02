package com.liupf.androidstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.listener.TranslucentListener;
import com.liupf.androidstudy.view.MyScrollView;

/**
 * 透明度变化的toolbar
 */
public class TranslucentScrollToolbarActivity extends AppCompatActivity implements TranslucentListener {

    public static void actionTranslucentScrollToolbarActivity(Context context){
        Intent intent=new Intent(context,TranslucentScrollToolbarActivity.class);
        context.startActivity(intent);
    }

    private Toolbar toolbar;
    private MyScrollView scv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_scroll_toolbar);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scv = (MyScrollView)findViewById(R.id.scrollView);
        scv.setListener(this);
    }

    @Override
    public void onTranlucent(float alpha) {
        toolbar.setAlpha(alpha);
    }
}
