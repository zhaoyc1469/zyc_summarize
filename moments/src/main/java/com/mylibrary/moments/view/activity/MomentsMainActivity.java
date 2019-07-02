package com.mylibrary.moments.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.moments.BR;
import com.mylibrary.moments.R;
import com.mylibrary.moments.databinding.ActivityMomentsMainBinding;
import com.mylibrary.moments.viewModel.MomentsMainViewModel;

@Route(path = "/moments/MomentsMainActivity")
public class MomentsMainActivity extends BaseActivity<ActivityMomentsMainBinding, MomentsMainViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_moments_main;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getUIChangeLiveData().getEndRefresh().observe(this,
                Void -> mDataBinding.srlContent.finishRefresh());
        mViewModel.getUIChangeLiveData().getEndLoadMore().observe(this,
                Void -> mDataBinding.srlContent.finishLoadMore());
    }
}