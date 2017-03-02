package com.liupf.androidstudy.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.liupf.androidstudy.R;

/**
 * 悬浮窗服务
 */
public class FloatWindowService extends Service {

    private final String TAG=this.getClass().getName();
    private WindowManager.LayoutParams mParams;
    private WindowManager windowManager;
    private View mWindowView;
    private TextView mPercentTv;

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;




    public FloatWindowService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
        initWindowParams();
        initView();
        addWindowView2Window();
        initClick();

    }

    private void initWindowParams() {
        windowManager= (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        mParams=new WindowManager.LayoutParams();
        mParams.type=WindowManager.LayoutParams.TYPE_PHONE;
        mParams.format= PixelFormat.TRANSLUCENT;        //窗口支持透明度
        mParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.gravity= Gravity.RIGHT|Gravity.CENTER;
        mParams.width=WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
//        DisplayMetrics metric = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
//        mParams.horizontalMargin=width-30;
//        mParams.
    }

    private void initView() {
        mWindowView= LayoutInflater.from(getApplication()).inflate(R.layout.layout_float_window,null);
        mPercentTv=(TextView)mWindowView.findViewById(R.id.percentTv);
        mPercentTv.setText("36%");
    }

    private void addWindowView2Window() {
        windowManager.addView(mWindowView,mParams);
    }

    private void initClick() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWindowView!=null){
            //移除悬浮窗口
            Log.i(TAG,"removeView");
            windowManager.removeView(mWindowView);
        }
        Log.i(TAG,"onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
