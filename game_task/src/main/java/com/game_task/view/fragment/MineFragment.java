package com.game_task.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseFragment;
import com.game_task.BR;
import com.game_task.R;
import com.game_task.databinding.FragmentMineBinding;
import com.game_task.viewModel.MineFrgFrgViewModel;

@Route(path = "/app/MineFragment")
public class MineFragment extends BaseFragment<FragmentMineBinding, MineFrgFrgViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViewObservable() {

    }
}