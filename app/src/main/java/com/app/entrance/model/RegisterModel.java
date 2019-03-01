package com.app.entrance.model;

import com.app.entrance.contract.IRegisterContract;
import com.app.frame.base.BaseModel;


public class RegisterModel extends BaseModel {

    private IRegisterContract.IRegisterViewModel viewModel;

    public RegisterModel(IRegisterContract.IRegisterViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
