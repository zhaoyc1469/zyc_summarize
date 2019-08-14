package com.game_task.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.game_task.contract.IHomeFrgViewModel;
import com.game_task.model.HomeModel;

public class HomeFrgFrgViewModel extends BaseViewModel<HomeModel> implements IHomeFrgViewModel {

    @Override
    protected HomeModel initModel(IViewModel ViewModel) {
        return new HomeModel(this);
    }
}
