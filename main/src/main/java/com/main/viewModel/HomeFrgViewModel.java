package com.main.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.main.contract.IHomeViewModel;
import com.main.model.HomeModel;

public class HomeFrgViewModel extends BaseViewModel<HomeModel> implements IHomeViewModel {

    @Override
    protected HomeModel initModel(IViewModel ViewModel) {
        return new HomeModel(this);
    }
}
