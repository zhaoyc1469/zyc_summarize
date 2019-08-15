package com.game_task.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.game_task.BR;
import com.game_task.R;
import com.game_task.databinding.ActivityGameDetailBinding;
import com.game_task.viewModel.GameDetailViewModel;

@Route(path = "/game_task/GameDetailActivity")
public class GameDetailActivity extends BaseActivity<ActivityGameDetailBinding, GameDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_game_detail;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}