package com.zyc_summarize.demo.view.activity;

import android.os.Bundle;

import com.zyc_summarize.base.base.BaseActivity;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.databinding.ActivitySplashBinding;
import com.zyc_summarize.demo.viewModel.SplashViewModel;


public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }
}
