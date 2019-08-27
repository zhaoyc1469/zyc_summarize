package com.camera.magiccamera.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.camera.magiccamera.BR;
import com.camera.magiccamera.R;
import com.camera.magiccamera.databinding.ActivityPlayBinding;
import com.camera.magiccamera.viewModel.PlayViewModel;

@Route(path = "/camera/PlayActivity")
public class PlayActivity extends BaseActivity<ActivityPlayBinding, PlayViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_play;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}