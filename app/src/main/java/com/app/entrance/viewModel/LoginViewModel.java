package com.app.entrance.viewModel;

import com.app.entrance.contract.ILoginViewModel;
import com.app.entrance.model.LoginModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;

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
