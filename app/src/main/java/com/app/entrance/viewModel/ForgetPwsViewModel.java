package com.app.entrance.viewModel;

import com.app.entrance.contract.IForgetPwsContract;
import com.app.entrance.model.ForgetPwsModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;

public class ForgetPwsViewModel extends BaseViewModel<IForgetPwsContract.IForgetPwsView, ForgetPwsModel> implements IForgetPwsContract.IForgetPwsViewModel {

    @Override
    protected ForgetPwsModel initModel(IViewModel ViewModel) {
        return new ForgetPwsModel(this);
    }
}
