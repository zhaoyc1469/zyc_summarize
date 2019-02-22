package com.app.entrance.model;

import com.app.entrance.contract.IForgetPwsViewModel;
import com.app.frame.base.BaseModel;


public class ForgetPwsModel extends BaseModel {

    private IForgetPwsViewModel viewModel;

    public ForgetPwsModel(IForgetPwsViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
