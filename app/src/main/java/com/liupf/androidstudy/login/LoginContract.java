package com.liupf.androidstudy.login;

import android.content.Context;

import com.liupf.androidstudy.mvp.BasePresenter;
import com.liupf.androidstudy.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        //在view层回调，根据Presenter逻辑调用
        void loginSuccess(Object user);

        void loginFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        //在view层调用，在Presenter中实现
        void login(String name,String password);
    }
}
