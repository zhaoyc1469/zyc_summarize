package com.zyc_summarize.demo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.zyc_summarize.base.BaseActivity;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.contract.SplashContract;
import com.zyc_summarize.demo.databinding.ActivitySplashBinding;
import com.zyc_summarize.demo.viewModel.SplashViewModel;


public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> implements SplashContract.View{


    @Override
    public ActivitySplashBinding onCreateDataBindingView(Bundle savedInstanceState) {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    public SplashViewModel onCreateViewModel(Bundle savedInstanceState) {
        return new SplashViewModel(this);
    }
}
