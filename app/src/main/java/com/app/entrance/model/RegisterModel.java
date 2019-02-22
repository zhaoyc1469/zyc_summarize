package com.app.entrance.model;

import com.app.entrance.contract.IRegisterViewModel;
import com.app.frame.base.BaseModel;


public class RegisterModel extends BaseModel {

    private IRegisterViewModel viewModel;

    public RegisterModel(IRegisterViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
