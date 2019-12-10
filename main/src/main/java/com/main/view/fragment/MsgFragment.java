package com.main.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.main.BR;
import com.main.R;
import com.main.databinding.FragmentMsgBinding;
import com.main.viewModel.MsgFrgViewModel;
import com.app.frame.base.BaseFragment;

public class MsgFragment extends BaseFragment<FragmentMsgBinding, MsgFrgViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_msg;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}