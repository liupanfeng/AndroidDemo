package com.liupf.androidstudy.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.bean.BaseInfo;
import com.liupf.androidstudy.bean.ContentInfo;

/**
 * Created by liupf on 2016/12/29.
 */

public class ContentHolder extends BaseViewHolder {

    private TextView tv_content;
    public ContentHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initViewHolder(View itemView, Object... obj) {
        tv_content= (TextView) itemView.findViewById(R.id.tv_content);
    }

    @Override
    public void bindViewHolder(Context context, BaseInfo info, View.OnClickListener listener) {
        tv_content.setText(((ContentInfo) info).getName());
        tv_content.setOnClickListener(listener);
        tv_content.setTag(info);
    }
}
