package com.app.entrance.viewModel;

import com.app.entrance.contract.ISplashContract;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.app.entrance.model.SplashModel;

public class SplashViewModel extends BaseViewModel<ISplashContract.ISplashView, SplashModel> implements ISplashContract.ISplashViewModel {

    @Override
    protected SplashModel initModel(IViewModel ViewModel) {
        return new SplashModel(this);
    }


    //跳过按钮的点击事件
    public BindingCommand skipClickCommand = new BindingCommand(this::skipAdvertising);

    private void skipAdvertising() {
        startActivity("/app/LoginActivity");
    }
}
