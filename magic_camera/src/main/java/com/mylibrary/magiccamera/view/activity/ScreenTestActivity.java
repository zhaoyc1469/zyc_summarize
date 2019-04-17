package com.mylibrary.magiccamera.view.activity;

import android.hardware.Camera;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.magiccamera.BR;
import com.mylibrary.magiccamera.R;
import com.mylibrary.magiccamera.databinding.ActivityScreenTestBinding;
import com.mylibrary.magiccamera.viewModel.ScreenTestViewModel;

@Route(path = "/camera/ScreenTestActivity")
public class ScreenTestActivity extends BaseActivity<ActivityScreenTestBinding, ScreenTestViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_screen_test;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        Camera.open();
    }
}