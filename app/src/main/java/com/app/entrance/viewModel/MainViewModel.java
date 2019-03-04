package com.app.entrance.viewModel;

import com.app.entrance.contract.IMainContract;
import com.app.entrance.model.MainModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;

public class MainViewModel extends BaseViewModel<IMainContract.IMainView,MainModel> implements IMainContract.IMainViewModel {

    @Override
    protected MainModel initModel(IViewModel ViewModel) {
        return new MainModel(this);
    }
}
