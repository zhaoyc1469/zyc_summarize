package com.game_task.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.game_task.BR;
import com.game_task.databinding.FragmentHomeBinding;
import com.app.frame.base.BaseFragment;
import com.game_task.R;
import com.game_task.viewModel.HomeFrgFrgViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFrgFrgViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}