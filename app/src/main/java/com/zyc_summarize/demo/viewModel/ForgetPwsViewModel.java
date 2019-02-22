package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.base.BaseViewModel;
import com.zyc_summarize.base.contract.IViewModel;
import com.zyc_summarize.demo.contract.IForgetPwsViewModel;
import com.zyc_summarize.demo.model.ForgetPwsModel;

public class ForgetPwsViewModel extends BaseViewModel<ForgetPwsModel> implements IForgetPwsViewModel {

    @Override
    protected ForgetPwsModel initModel(IViewModel ViewModel) {
        return new ForgetPwsModel(this);
    }
}
