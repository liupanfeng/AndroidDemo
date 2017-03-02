package com.liupf.androidstudy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class KeyBoardActivity extends AppCompatActivity {

    public static void actionKeyBoardActivity(Context context){
        Intent intent=new Intent(context,KeyBoardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
    }


    public void onPauseCall(View view){

    }

    public void onVideoCall(View view){

    }

    public void onTheme(View view){

    }

    public void onDtmf(View view){

    }
}
