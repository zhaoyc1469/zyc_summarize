package com.zyc_summarize.demo.view.activity;

import android.os.Bundle;

import com.zyc_summarize.base.base.BaseActivity;
import com.zyc_summarize.demo.BR;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.databinding.ActivityRegisterBinding;
import com.zyc_summarize.demo.viewModel.RegisterViewModel;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
