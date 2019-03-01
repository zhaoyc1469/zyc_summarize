package com.app.entrance.viewModel;

import com.app.entrance.contract.IRegisterContract;
import com.app.entrance.model.RegisterModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;

public class RegisterViewModel extends BaseViewModel<IRegisterContract.IRegisterView, RegisterModel> implements IRegisterContract.IRegisterViewModel {

    @Override
    protected RegisterModel initModel(IViewModel ViewModel) {
        return new RegisterModel(this);
    }

    @Override
    public void sendSMS() {

    }

    @Override
    public void register() {

    }
}
