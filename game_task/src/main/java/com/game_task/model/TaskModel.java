package com.game_task.model;

import com.app.frame.base.BaseModel;
import com.game_task.contract.ITaskFrgViewModel;


public class TaskModel extends BaseModel<ITaskFrgViewModel> {

    public TaskModel(ITaskFrgViewModel mViewModel) {
        super(mViewModel);
    }

}