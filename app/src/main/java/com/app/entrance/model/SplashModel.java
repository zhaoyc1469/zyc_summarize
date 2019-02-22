package com.app.entrance.model;

import com.app.entrance.contract.ISplashViewModel;
import com.app.frame.base.BaseModel;


public class SplashModel extends BaseModel {

    private ISplashViewModel viewModel;

    public SplashModel(ISplashViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
