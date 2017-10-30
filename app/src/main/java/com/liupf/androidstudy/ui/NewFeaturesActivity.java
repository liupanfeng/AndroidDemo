package com.liupf.androidstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.liupf.androidstudy.R;

/**
 * 5.0新特性
 */
public class NewFeaturesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    public static void actionNewFeaturesActivity(Context context){
        Intent intent=new Intent(context,NewFeaturesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meatures);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("特性类别");
        toolbar.setSubtitle("每个功能点详细介绍");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.pic);
    }
}
