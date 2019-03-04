package com.app.entrance.model;

import com.app.entrance.contract.IRegisterContract;
import com.app.frame.base.BaseModel;


public class RegisterModel extends BaseModel<IRegisterContract.IRegisterViewModel> {

    public RegisterModel(IRegisterContract.IRegisterViewModel mViewModel) {
        super(mViewModel);
    }
}
