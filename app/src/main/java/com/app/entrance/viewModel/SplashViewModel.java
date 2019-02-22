package com.app.entrance.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.app.entrance.contract.ISplashViewModel;
import com.app.entrance.model.SplashModel;

public class SplashViewModel extends BaseViewModel<SplashModel> implements ISplashViewModel {

    @Override
    protected SplashModel initModel(IViewModel ViewModel) {
        return new SplashModel(this);
    }


    //跳过按钮的点击事件
    public BindingCommand skipClickCommand = new BindingCommand(this::skipAdvertising);

    private void skipAdvertising() {
        uiChangeLiveData.getFinishEvent().postValue(null);
    }
}
