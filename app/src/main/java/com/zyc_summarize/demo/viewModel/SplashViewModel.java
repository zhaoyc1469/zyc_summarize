package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.base.BaseViewModel;
import com.zyc_summarize.base.binding.command.BindingCommand;
import com.zyc_summarize.base.contract.IViewModel;
import com.zyc_summarize.demo.contract.ISplashViewModel;
import com.zyc_summarize.demo.model.SplashModel;

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
