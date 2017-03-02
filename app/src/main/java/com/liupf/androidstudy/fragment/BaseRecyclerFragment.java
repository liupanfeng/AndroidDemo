package com.liupf.androidstudy.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liupf on 2016/12/29.
 */

public abstract class BaseRecyclerFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;

    private ConcurrentHashMap<String, Future<?>> mTask = new ConcurrentHashMap<>();         //这个用于放任务
    private ExecutorService mThreadPool;
    protected BaseRecyclerAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mThreadPool= Executors.newFixedThreadPool(3);                   //newFixedThreadPool 这个方法可以设置线程的个数，多余这个数量就等待

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


}
