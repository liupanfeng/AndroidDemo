package com.liupf.androidstudy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.liupf.androidstudy.ui.BaseActivity;
import com.liupf.androidstudy.manager.MFragmentManager;

/**
 * Created by liupf on 2016/12/29.
 */

public abstract class BaseFragment extends Fragment  {

    protected int screenWidth;
    protected BaseActivity mActivity;
    protected MyHandler mMyHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity= (BaseActivity) getActivity();
        mMyHandler=new MyHandler();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        screenWidth=mActivity.getResources().getDisplayMetrics().widthPixels;
        MFragmentManager.keepFragment(this);    //讲所有的fragment存储起来
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMyHandler!=null){
            mMyHandler.removeCallbacksAndMessages(null);
        }
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMyMessage(msg);
        }
    }

    public abstract void handleMyMessage(Message msg);

    public int getFragmentType(){

        Bundle args=getArguments();
        return args==null?0:args.getInt("type",0);
    }
}
