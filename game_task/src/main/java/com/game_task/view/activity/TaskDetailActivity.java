package com.game_task.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.game_task.BR;
import com.game_task.R;
import com.game_task.databinding.ActivityTaskDetailBinding;
import com.game_task.viewModel.TaskDetailViewModel;

@Route(path = "/game_task/TaskDetailActivity")
public class TaskDetailActivity extends BaseActivity<ActivityTaskDetailBinding, TaskDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_task_detail;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}