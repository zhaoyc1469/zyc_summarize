package com.app.entrance.model;

import com.app.entrance.contract.IRegisterContract;
import com.app.entrance.contract.ISplashContract;
import com.app.frame.base.BaseModel;


public class SplashModel extends BaseModel<IRegisterContract.IRegisterViewModel> {

    public SplashModel(IRegisterContract.IRegisterViewModel mViewModel) {
        super(mViewModel);
    }
}
