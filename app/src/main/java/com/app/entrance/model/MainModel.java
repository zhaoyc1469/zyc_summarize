package com.app.entrance.model;

import com.app.frame.base.BaseModel;
import com.app.entrance.contract.IMainViewModel;


public class MainModel extends BaseModel {

    private IMainViewModel viewModel;

    public MainModel(IMainViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
