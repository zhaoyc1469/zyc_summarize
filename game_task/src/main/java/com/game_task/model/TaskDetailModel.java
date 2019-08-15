package com.game_task.model;

import com.app.frame.base.BaseModel;
import com.game_task.contract.ITaskDetailViewModel;


public class TaskDetailModel extends BaseModel<ITaskDetailViewModel> {

    public TaskDetailModel(ITaskDetailViewModel mViewModel) {
        super(mViewModel);
    }

}