package com.zyc_summarize.demo.view.activity;

import android.os.Bundle;

import com.zyc_summarize.base.base.BaseActivity;
import com.zyc_summarize.demo.BR;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.databinding.ActivityLoginBinding;
import com.zyc_summarize.demo.viewModel.LoginViewModel;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
