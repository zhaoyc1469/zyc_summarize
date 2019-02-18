package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.BaseViewModel;
import com.zyc_summarize.demo.contract.SplashContract;

public class SplashViewModel extends BaseViewModel implements SplashContract.Model {
    private SplashContract.View mView;

    public SplashViewModel(SplashContract.View mView) {
        this.mView = mView;
    }
}
