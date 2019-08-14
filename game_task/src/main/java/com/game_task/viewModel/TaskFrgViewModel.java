package com.game_task.viewModel;

import androidx.databinding.ObservableField;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.game_task.contract.ITaskFrgViewModel;
import com.game_task.model.TaskModel;

public class TaskFrgViewModel extends BaseViewModel<TaskModel> implements ITaskFrgViewModel {

    public ObservableField<Integer> showType = new ObservableField<>(0);

    @Override
    protected TaskModel initModel(IViewModel ViewModel) {
        return new TaskModel(this);
    }
}
