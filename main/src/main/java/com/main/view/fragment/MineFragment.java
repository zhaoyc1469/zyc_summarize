package com.main.view.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.app.frame.base.BaseFragment;
import com.main.BR;
import com.main.R;
import com.main.databinding.FragmentMineBinding;
import com.main.viewModel.MineFrgViewModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

public class MineFragment extends BaseFragment<FragmentMineBinding, MineFrgViewModel> {


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
        mViewModel.getScreenClickEvent().observe(this, o -> {
            RxPermissions rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {

                        }
                    });
        });
    }
}