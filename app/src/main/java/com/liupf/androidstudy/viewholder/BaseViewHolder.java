package com.liupf.androidstudy.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liupf.androidstudy.bean.BaseInfo;

/**
 * Created by liupf on 2016/12/29.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView,Object... obj) {
        super(itemView);

        initViewHolder(itemView,obj);
    }


    /**
     * 初始化ViewHolder布局元素
     *
     * @param itemView
     * @param obj
     */
    protected abstract void initViewHolder(View itemView, Object... obj);

    /**
     * 绑定Viewholder数据
     *
     * @param context
     * @param info
     * @param listener
     */
    public abstract void bindViewHolder(Context context, BaseInfo info, View.OnClickListener listener);


}
