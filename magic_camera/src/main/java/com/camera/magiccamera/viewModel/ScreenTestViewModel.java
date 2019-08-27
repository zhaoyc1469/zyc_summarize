package com.camera.magiccamera.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;
import com.camera.magiccamera.contract.IScreenTestViewModel;
import com.camera.magiccamera.model.ScreenTestModel;

public class ScreenTestViewModel extends BaseViewModel<ScreenTestModel> implements IScreenTestViewModel {

    private SingleLiveEvent<Integer> photoClickEvent;

    @Override
    protected ScreenTestModel initModel(IViewModel ViewModel) {
        return new ScreenTestModel(this);
    }


    public SingleLiveEvent<Integer> getPhotoClickEvent() {
        if (photoClickEvent == null) {
            photoClickEvent = new SingleLiveEvent<>();
        }
        return photoClickEvent;
    }

    public BindingCommand cameraClick = new BindingCommand(() -> photoClickEvent.postValue(null));
}
