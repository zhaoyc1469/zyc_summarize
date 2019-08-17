package com.game_task.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;
import com.app.frame.down.InstallUtils;
import com.game_task.contract.ITaskDetailViewModel;
import com.game_task.model.TaskDetailModel;

public class TaskDetailViewModel extends BaseViewModel<TaskDetailModel> implements ITaskDetailViewModel {

    private SingleLiveEvent<String> downClickEvent;

    @Override
    protected TaskDetailModel initModel(IViewModel ViewModel) {
        return new TaskDetailModel(this);
    }


    public SingleLiveEvent<String> getDownClickEvent() {
        if (downClickEvent == null) {
            downClickEvent = new SingleLiveEvent<>();
        }
        return downClickEvent;
    }

    public BindingCommand receiveClick = new BindingCommand(() -> {

    });

    public BindingCommand downClick = new BindingCommand(() -> getDownClickEvent().postValue(""));

    public BindingCommand downCancelClick = new BindingCommand(() -> {

    });
}
