package com.liupf.androidstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.liupf.androidstudy.Adapter.MyExpandableListViewAdapter;
import com.liupf.androidstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可扩展的listview
 */
public class ExpandableListViewActivity extends AppCompatActivity {


    public  static void  actionExpandableListViewActivity(Context context){
        Intent intent=new Intent(context,ExpandableListViewActivity.class);
        context.startActivity(intent);
    }
    private ExpandableListView expandableListView;
    private Map<String,List<String>> mDataMap=new HashMap<>();
    private String[] mParentDataArr={"王昭君","Lucy","Toms"};

    private List<String> mChildDataList1=new ArrayList<>();
    private List<String> mChildDataList2=new ArrayList<>();
    private List<String> mChildDataList3=new ArrayList<>();
    private MyExpandableListViewAdapter mMyExpandableListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        initData();
        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
        mMyExpandableListViewAdapter=new MyExpandableListViewAdapter(this,mDataMap);
        expandableListView.setAdapter(mMyExpandableListViewAdapter);
        expandableListView.setGroupIndicator(null);
    }

    private void initData() {
        mChildDataList1.add("王昭君--1");
        mChildDataList1.add("王昭君--2");
        mChildDataList1.add("王昭君--3");

        mChildDataList2.add("Lucy--1");
        mChildDataList2.add("Lucy--2");
        mChildDataList2.add("Lucy--3");

        mChildDataList3.add("Toms--1");
        mChildDataList3.add("Toms--2");
        mChildDataList3.add("Toms--3");

        mDataMap.put(mParentDataArr[0],mChildDataList1);
        mDataMap.put(mParentDataArr[1],mChildDataList2);
        mDataMap.put(mParentDataArr[2],mChildDataList3);



    }
}
