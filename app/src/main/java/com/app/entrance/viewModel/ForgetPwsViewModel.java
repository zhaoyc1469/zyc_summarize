package com.app.entrance.viewModel;

import com.app.entrance.contract.IForgetPwsViewModel;
import com.app.entrance.model.ForgetPwsModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;

public class ForgetPwsViewModel extends BaseViewModel<ForgetPwsModel> implements IForgetPwsViewModel {

    @Override
    protected ForgetPwsModel initModel(IViewModel ViewModel) {
        return new ForgetPwsModel(this);
    }
}
