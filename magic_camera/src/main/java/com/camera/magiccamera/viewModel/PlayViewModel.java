package com.camera.magiccamera.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.camera.magiccamera.contract.IPlayViewModel;
import com.camera.magiccamera.model.PlayModel;

public class PlayViewModel extends BaseViewModel<PlayModel> implements IPlayViewModel {

    @Override
    protected PlayModel initModel(IViewModel ViewModel) {
        return new PlayModel(this);
    }
}
