package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.base.BaseViewModel;
import com.zyc_summarize.base.contract.IViewModel;
import com.zyc_summarize.demo.contract.IRegisterViewModel;
import com.zyc_summarize.demo.model.RegisterModel;

public class RegisterViewModel extends BaseViewModel<RegisterModel> implements IRegisterViewModel {

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
