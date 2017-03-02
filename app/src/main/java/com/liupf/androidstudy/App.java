package com.liupf.androidstudy;

import android.app.Application;

/**
 * Created by liupf on 2016/12/29.
 */

public class App extends Application {

    private static App instance;

    public static App getInstance(){
        if (instance!=null){
            return instance;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
