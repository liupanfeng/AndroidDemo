package com.liupf.androidstudy.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liupf.androidstudy.ui.AttributeAnimationActivity;
import com.liupf.androidstudy.ui.ExpandableListViewActivity;
import com.liupf.androidstudy.ui.FloatWindowActivity;
import com.liupf.androidstudy.ui.MaterialDesignPaletteActivity;
import com.liupf.androidstudy.R;
import com.liupf.androidstudy.ui.NewFeaturesActivity;
import com.liupf.androidstudy.ui.TabLayoutActivity;
import com.liupf.androidstudy.ui.TranslucentScrollToolbarActivity;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;
import com.liupf.androidstudy.util.Contants;
import com.liupf.androidstudy.viewholder.BaseViewHolder;
import com.liupf.androidstudy.viewholder.ContentHolder;
import com.liupf.androidstudy.ui.KeyBoardActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupf on 2016/12/29.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected RecyclerView mRecyclerView;
    protected List<BaseInfo> mList;
    protected MyHandler mHandler;
    private String name="lpf";

    public BaseRecyclerAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mRecyclerView = recyclerView;
        mList = new ArrayList<>();
        mHandler = new MyHandler();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        View itemView;
        itemView=mLayoutInflater.inflate(R.layout.layout_item_main,parent,false);
        holder=new ContentHolder(itemView);
        name="weiwei";
        return holder;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setIsRecyclable(true);
        Log.d("--",name);
        holder.bindViewHolder(mContext, getItem(position), this);
    }



    /**
     * 获得素材元素
     *
     * @param position
     * @return
     */
    public BaseInfo getItem(int position) {
        if (position < 0 || mList == null || position >= mList.size()) return new BaseInfo();
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View v) {
        onItemClick(v);
    }

    private void onItemClick(View v) {
        BaseInfo info= (BaseInfo) v.getTag();
        if(info instanceof ContentInfo){
            switch (((ContentInfo) info).getName()){
                case "拨号键":
                    KeyBoardActivity.actionKeyBoardActivity(mContext);
                    break;
                case "使用WindowManager实现悬浮窗":
                    FloatWindowActivity.actionFloatWindowActivity(mContext);
                    break;
                case "MaterialDesign动画-palette":
                    MaterialDesignPaletteActivity.actionMaterialDesignPaletteActivity(mContext);
                    break;
                case "透明度变化的toolbar":
                    TranslucentScrollToolbarActivity.actionTranslucentScrollToolbarActivity(mContext);
                    break;
                case "TabLayout的简单使用":
                    TabLayoutActivity.actionTabLayoutActivity(mContext);
                    break;
                case "MVP_LOGIN":
//                    LoginActivity.actionLoginActivity(mContext);
                    break;
                case Contants.NEW_FEATURES:
                    NewFeaturesActivity.actionNewFeaturesActivity(mContext);
                    break;
                case Contants.EXPANDABLE_LISTVIEW:
                    ExpandableListViewActivity.actionExpandableListViewActivity(mContext);
                    break;

                case Contants.ATTRIBUTE_ANIMATION:
                    AttributeAnimationActivity.actionAttributeAnimationActivity(mContext);
                    break;
            }
        }
    }



    public void addAll(List<? extends BaseInfo> list){
        if (list!=null&&list.size()>0){
            mList.clear();
            mList.addAll(list);
        }else{
            mList.clear();
        }
        notifyDataSetChanged();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMyMessage(msg);
        }
    }

    protected void handleMyMessage(Message msg) {

    }


}
