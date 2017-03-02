package com.liupf.androidstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.liupf.androidstudy.listener.TranslucentListener;

/**
 * Created by lpf on 2017/3/2.
 */

public class MyScrollView extends ScrollView {

    private TranslucentListener mTranslucentListener;

    public void setListener(TranslucentListener listener) {
        this.mTranslucentListener = listener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滑动变化监听
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mTranslucentListener!=null){
            int scrollY = getScrollY();
            int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
            if(scrollY<=screen_height/3f){//0~1f,而透明度应该是1~0f
                mTranslucentListener.onTranlucent(1-scrollY/(screen_height/3f));//alpha=滑出去的高度/(screen_height/3f)
            }

        }
    }
}
