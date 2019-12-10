package com.main.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;
import com.main.contract.IMainViewModel;
import com.main.model.MainModel;

public class MainViewModel extends BaseViewModel<MainModel> implements IMainViewModel {

    private SingleLiveEvent<Integer> bottomClickEvent;

    @Override
    protected MainModel initModel(IViewModel ViewModel) {
        return new MainModel(this);
    }


    public SingleLiveEvent<Integer> getBottomClickEvent() {
        if (bottomClickEvent == null) {
            bottomClickEvent = new SingleLiveEvent<>();
        }
        return bottomClickEvent;
    }

    public BindingCommand msgClick = new BindingCommand(() -> bottomClickEvent.postValue(1));
    public BindingCommand taskClick = new BindingCommand(() -> bottomClickEvent.postValue(2));
    public BindingCommand projectClick = new BindingCommand(() -> bottomClickEvent.postValue(3));
    public BindingCommand flowClick = new BindingCommand(() -> bottomClickEvent.postValue(4));
    public BindingCommand mineClick = new BindingCommand(() -> bottomClickEvent.postValue(5));
}

