package com.game_task.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseFragment;
import com.game_task.BR;
import com.game_task.R;
import com.game_task.databinding.FragmentTaskBinding;
import com.game_task.viewModel.TaskFrgViewModel;

@Route(path = "/game_task/TaskFragment")
public class TaskFragment extends BaseFragment<FragmentTaskBinding, TaskFrgViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_task;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

}