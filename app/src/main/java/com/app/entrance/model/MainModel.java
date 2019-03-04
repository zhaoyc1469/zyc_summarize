package com.app.entrance.model;

import com.app.entrance.contract.IMainContract;
import com.app.frame.base.BaseModel;


public class MainModel extends BaseModel<IMainContract.IMainViewModel> {

    public MainModel(IMainContract.IMainViewModel mViewModel) {
        super(mViewModel);
    }
}
