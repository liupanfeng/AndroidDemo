package com.liupf.androidstudy.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;

/**
 * Created by liupf on 2017/1/19.
 */

public abstract class BaseItemDecoration extends RecyclerView.ItemDecoration {

    protected int mSpace;
    protected BaseRecyclerAdapter mAdapter;

    public BaseItemDecoration(int space, BaseRecyclerAdapter adapter) {
        mSpace = space;
        mAdapter = adapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        handleItemOffsets(outRect, view, parent, state, position);
    }

    protected abstract void handleItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position);
}
