package com.app.entrance.model;

import com.app.entrance.contract.ISplashContract;
import com.app.frame.base.BaseModel;


public class SplashModel extends BaseModel {

    private ISplashContract.ISplashViewModel viewModel;

    public SplashModel(ISplashContract.ISplashViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
