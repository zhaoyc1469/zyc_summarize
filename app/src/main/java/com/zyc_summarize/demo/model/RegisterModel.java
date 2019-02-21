package com.zyc_summarize.demo.model;

import com.zyc_summarize.base.base.BaseModel;
import com.zyc_summarize.demo.contract.IRegisterViewModel;


public class RegisterModel extends BaseModel {

    private IRegisterViewModel viewModel;

    public RegisterModel(IRegisterViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
