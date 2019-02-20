package com.zyc_summarize.demo.model;

import com.zyc_summarize.base.base.BaseModel;
import com.zyc_summarize.demo.contract.IMainViewModel;


public class MainModel extends BaseModel {

    private IMainViewModel viewModel;

    public MainModel(IMainViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
