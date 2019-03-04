package com.app.entrance.model;

import com.app.entrance.contract.IMainViewContract;
import com.app.frame.base.BaseModel;


public class MainModel extends BaseModel<IMainViewContract.IMainViewModel> {

    public MainModel(IMainViewContract.IMainViewModel mViewModel) {
        super(mViewModel);
    }
}
