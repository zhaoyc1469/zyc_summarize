package com.main.model;

import com.main.contract.IMainViewModel;
import com.app.frame.base.BaseModel;


public class MainModel extends BaseModel<IMainViewModel> {

    public MainModel(IMainViewModel mViewModel) {
        super(mViewModel);
    }
}
