package com.liupf.androidstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.service.FloatWindowService;

public class FloatWindowActivity extends AppCompatActivity {

    public static void actionFloatWindowActivity(Context context){
        Intent intent=new Intent(context,FloatWindowActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);
    }


    public void onOpen(View view){
        Intent intent=new Intent(this,FloatWindowService.class);
        this.startService(intent);
    }

    public void onClose(View view){
        Intent intent=new Intent(this,FloatWindowService.class);
        this.stopService(intent);
    }
}
