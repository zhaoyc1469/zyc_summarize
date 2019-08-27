package com.main.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.main.BR;
import com.main.R;
import com.main.databinding.FragmentHomeBinding;
import com.main.viewModel.HomeFrgViewModel;
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