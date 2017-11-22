package com.liupf.androidstudy.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liupf.androidstudy.R;
import com.liupf.androidstudy.mvp.MVPBaseActivity;
import com.liupf.androidstudy.ui.AttributeAnimationActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private Button btnLogin;

    public static void actionLoginActivity(Context context){
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login("test","123456");
            }
        });
    }

    @Override
    public void loginSuccess(Object user) {
        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();

    }
}
