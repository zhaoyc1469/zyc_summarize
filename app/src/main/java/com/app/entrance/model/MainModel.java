package com.app.entrance.model;

import com.app.entrance.contract.IMainViewContract;
import com.app.frame.base.BaseModel;


public class MainModel extends BaseModel {

    private IMainViewContract.IMainViewModel viewModel;

    public MainModel(IMainViewContract.IMainViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
