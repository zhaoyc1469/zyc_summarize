package com.app.entrance.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.databinding.FragmentHomeBinding;
import com.app.entrance.viewModel.HomeFrgViewModel;
import com.app.frame.base.BaseFragment;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFrgViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}