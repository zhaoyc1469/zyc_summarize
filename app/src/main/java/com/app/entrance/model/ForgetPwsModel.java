package com.app.entrance.model;

import com.app.entrance.contract.IForgetPwsContract;
import com.app.frame.base.BaseModel;


public class ForgetPwsModel extends BaseModel {

    private IForgetPwsContract.IForgetPwsViewModel viewModel;

    public ForgetPwsModel(IForgetPwsContract.IForgetPwsViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
