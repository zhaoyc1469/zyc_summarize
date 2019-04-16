package com.app.entrance.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.app.entrance.contract.IHomeViewModel;
import com.app.entrance.model.HomeModel;

public class HomeFrgViewModel extends BaseViewModel<HomeModel> implements IHomeViewModel {

    @Override
    protected HomeModel initModel(IViewModel ViewModel) {
        return new HomeModel(this);
    }
}
