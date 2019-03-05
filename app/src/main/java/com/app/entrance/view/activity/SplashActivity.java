package com.app.entrance.view.activity;

import android.os.Bundle;

import com.app.entrance.contract.ISplashContract;
import com.app.entrance.viewModel.SplashViewModel;
import com.app.frame.base.BaseActivity;
import com.app.entrance.databinding.ActivitySplashBinding;
import com.app.entrance.R;
import com.app.entrance.BR;

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> implements ISplashContract.ISplashView {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        mViewModel.initData();
    }
}
