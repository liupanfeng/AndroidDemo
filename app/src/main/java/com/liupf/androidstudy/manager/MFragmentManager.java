package com.liupf.androidstudy.manager;

import com.liupf.androidstudy.fragment.BaseFragment;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liupf on 2016/12/29.
 */

public class MFragmentManager {

    private static ConcurrentHashMap<BaseFragment,String> mFragmentMap=new ConcurrentHashMap<>();

    public static void keepFragment(BaseFragment fragment){
        mFragmentMap.put(fragment,fragment.getClass().getName());
    }

    public static void removeFragment(BaseFragment fragment){
        if (mFragmentMap.containsKey(fragment)){
            mFragmentMap.remove(fragment);
        }
    }



}
