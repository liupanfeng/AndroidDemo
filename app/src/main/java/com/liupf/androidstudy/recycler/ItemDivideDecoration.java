package com.liupf.androidstudy.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liupf.androidstudy.Adapter.BaseRecyclerAdapter;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;

/**
 * Created by liupf on 2017/1/19.
 */

public class ItemDivideDecoration extends BaseItemDecoration  {

    public ItemDivideDecoration(int space, BaseRecyclerAdapter adapter) {
        super(space, adapter);
    }

    @Override
    protected void handleItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position) {

        if (position < 0) return;
        BaseInfo info = mAdapter.getItem(position);

        if (info instanceof ContentInfo) {

            outRect.top = (int) (mSpace * 2.0 / 3);

        }
    }
}
