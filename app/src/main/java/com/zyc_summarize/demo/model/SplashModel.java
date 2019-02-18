package com.zyc_summarize.demo.model;

import com.zyc_summarize.base.base.BaseModel;
import com.zyc_summarize.demo.contract.ISplashViewModel;


public class SplashModel extends BaseModel {

    private  ISplashViewModel viewModel;

    public SplashModel(ISplashViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    private void getLog(){

    }
}
