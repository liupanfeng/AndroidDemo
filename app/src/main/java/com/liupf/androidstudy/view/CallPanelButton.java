package com.liupf.androidstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by liupf on 2017/1/20.
 */

public class CallPanelButton extends RelativeLayout{

    public CallPanelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i=0;i<getChildCount();i++){
            getChildAt(i).setEnabled(enabled);
        }
    }
}
