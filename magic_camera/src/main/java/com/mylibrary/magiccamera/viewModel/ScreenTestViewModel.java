package com.mylibrary.magiccamera.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.magiccamera.contract.IScreenTestViewModel;
import com.mylibrary.magiccamera.model.ScreenTestModel;

public class ScreenTestViewModel extends BaseViewModel<ScreenTestModel> implements IScreenTestViewModel {

    @Override
    protected ScreenTestModel initModel(IViewModel ViewModel) {
        return new ScreenTestModel(this);
    }
}
