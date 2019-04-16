package com.app.entrance.view.activity;

import android.os.Bundle;

import com.app.frame.base.BaseActivity;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.databinding.ActivityForgetPwsBinding;
import com.app.entrance.viewModel.ForgetPwsViewModel;


public class ForgetPwsActivity extends BaseActivity<ActivityForgetPwsBinding, ForgetPwsViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_forget_pws;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
