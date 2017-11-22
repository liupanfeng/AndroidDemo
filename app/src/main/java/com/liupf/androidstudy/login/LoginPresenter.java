package com.liupf.androidstudy.login;

import android.content.Context;

import com.liupf.androidstudy.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(String name, String password) {
        if ("test".equals(name)&&"123456".equals(password)){
            mView.loginSuccess(null);
        }else{
            mView.loginFail("login fail");
        }
    }
}
