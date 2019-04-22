package com.app.entrance.view.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.entrance.databinding.FragmentMineBinding;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.viewModel.MineFrgViewModel;
import com.app.frame.base.BaseFragment;
import com.mylibrary.magiccamera.view.activity.ScreenTestActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

@Route(path = "/app/MineFragment")
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
                            startActivity(ScreenTestActivity.class);
                        } else {

                        }
                    });
        });
    }
}