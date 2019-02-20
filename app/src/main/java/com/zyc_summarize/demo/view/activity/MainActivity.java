package com.zyc_summarize.demo.view.activity;

import android.os.Bundle;
import android.view.View;

import com.zyc_summarize.base.base.BaseActivity;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.databinding.ActivityMainBinding;
import com.zyc_summarize.demo.viewModel.MainViewModel;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        super.initData();
    }
}
