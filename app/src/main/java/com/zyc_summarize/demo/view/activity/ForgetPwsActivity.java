package com.zyc_summarize.demo.view.activity;

import android.os.Bundle;

import com.zyc_summarize.base.base.BaseActivity;
import com.zyc_summarize.demo.BR;
import com.zyc_summarize.demo.R;
import com.zyc_summarize.demo.databinding.ActivityForgetPwsBinding;
import com.zyc_summarize.demo.viewModel.ForgetPwsViewModel;


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
