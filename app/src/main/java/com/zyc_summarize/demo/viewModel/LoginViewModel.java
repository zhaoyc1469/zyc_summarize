package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.base.BaseViewModel;
import com.zyc_summarize.base.binding.command.BindingCommand;
import com.zyc_summarize.base.contract.IViewModel;
import com.zyc_summarize.demo.contract.ILoginViewModel;
import com.zyc_summarize.demo.model.LoginModel;

public class LoginViewModel extends BaseViewModel<LoginModel> implements ILoginViewModel {

    @Override
    protected LoginModel initModel(IViewModel ViewModel) {
        return new LoginModel(this);
    }

    //跳过按钮的点击事件
    public BindingCommand loginClickCommand = new BindingCommand(this::login);

    private void login() {
        mModel.login();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void requestFail(String f) {
    }
}
