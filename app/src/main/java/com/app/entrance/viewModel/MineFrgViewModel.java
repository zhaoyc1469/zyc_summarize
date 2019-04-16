package com.app.entrance.viewModel;

import com.app.entrance.contract.IMineViewModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.app.entrance.model.MineModel;
import com.mylibrary.magiccamera.view.activity.ScreenTestActivity;

public class MineFrgViewModel extends BaseViewModel<MineModel> implements IMineViewModel {

    @Override
    protected MineModel initModel(IViewModel ViewModel) {
        return new MineModel(this);
    }



    public BindingCommand cameraClick = new BindingCommand(() -> startActivity(ScreenTestActivity.class));
}
