package com.app.entrance.view.activity;

import android.os.Bundle;

import com.app.entrance.contract.IRegisterContract;
import com.app.entrance.viewModel.RegisterViewModel;
import com.app.frame.base.BaseActivity;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.databinding.ActivityRegisterBinding;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements IRegisterContract.IRegisterView {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
