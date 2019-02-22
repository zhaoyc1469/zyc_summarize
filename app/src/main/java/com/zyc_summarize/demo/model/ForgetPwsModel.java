package com.zyc_summarize.demo.model;

import com.zyc_summarize.base.base.BaseModel;
import com.zyc_summarize.demo.contract.IForgetPwsViewModel;


public class ForgetPwsModel extends BaseModel {

    private IForgetPwsViewModel viewModel;

    public ForgetPwsModel(IForgetPwsViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
