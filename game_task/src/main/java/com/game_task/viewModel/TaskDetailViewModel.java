package com.game_task.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.game_task.contract.ITaskDetailViewModel;
import com.game_task.model.TaskDetailModel;

public class TaskDetailViewModel extends BaseViewModel<TaskDetailModel> implements ITaskDetailViewModel {

    @Override
    protected TaskDetailModel initModel(IViewModel ViewModel) {
        return new TaskDetailModel(this);
    }

    public BindingCommand photoClick = new BindingCommand(() -> {

    });
}
