package com.app.entrance.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.app.entrance.viewModel.LoginViewModel;
import com.app.frame.manager.AppManager;
import com.app.entrance.R;
import com.app.entrance.BR;
import com.app.entrance.databinding.ActivityLoginBinding;

@Route(path = "/app/LoginActivity")
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        AppManager.getAppManager().removeOtherActivity(LoginActivity.class);
    }
}
