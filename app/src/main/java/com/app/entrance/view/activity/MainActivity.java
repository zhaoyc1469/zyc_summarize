package com.app.entrance.view.activity;

import android.os.Bundle;

import com.app.entrance.viewModel.MainViewModel;
import com.app.frame.base.BaseActivity;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

}
