package com.liupf.androidstudy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liupf.androidstudy.R;

import java.util.List;
import java.util.Map;

/**
 * Created by lpf on 17-11-1.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Map<String,List<String>> mDataMap;
    private List<String> mList;
    private Context mContext;
    private String[] mParentDataArr={"王昭君","Lucy","Toms"};
    private LayoutInflater mInflater;

    public MyExpandableListViewAdapter(Context context, Map<String,List<String>> map){
        this.mDataMap=map;
        this.mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    /**
     * 获取父项的数量
     * @return
     */
    @Override
    public int getGroupCount() {
        return mDataMap.size();
    }

    /**
     * 获取某个父项的子项的数量
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataMap.get(mParentDataArr[groupPosition]).size();
    }

    /**
     *
     获得某个父项
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mParentDataArr[groupPosition];
    }

    /**
     * 获得某个子项
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataMap.get(mParentDataArr[groupPosition]).get(childPosition);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.parent_item,parent,false);
        }
        TextView tv= (TextView) convertView.findViewById(R.id.parent_title);
        tv.setText(mParentDataArr[groupPosition]);
        ImageView iv= (ImageView) convertView.findViewById(R.id.iv_indicator);
        if (isExpanded){
            iv.setImageResource(R.mipmap.icon_arrow_down);
        }else{
            iv.setImageResource(R.mipmap.icon_arrow_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.child_item,parent,false);
        }
        TextView tv= (TextView) convertView.findViewById(R.id.child_title);
        tv.setText(mDataMap.get(mParentDataArr[groupPosition]).get(childPosition));
        return convertView;
    }

    /**
     * 子项是否可选中，如果需要给子项设置点击事件，需要返回false
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
