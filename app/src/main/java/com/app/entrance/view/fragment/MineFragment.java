package com.app.entrance.view.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.entrance.databinding.FragmentMineBinding;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.viewModel.MineFrgViewModel;
import com.app.frame.base.BaseFragment;

@Route(path = "/app/MineFragment")
public class MineFragment extends BaseFragment<FragmentMineBinding, MineFrgViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}