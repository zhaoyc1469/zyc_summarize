package com.game_task.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.game_task.contract.IGameDetailViewModel;
import com.game_task.model.GameDetailModel;

public class GameDetailViewModel extends BaseViewModel<GameDetailModel> implements IGameDetailViewModel {

    @Override
    protected GameDetailModel initModel(IViewModel ViewModel) {
        return new GameDetailModel(this);
    }
}
