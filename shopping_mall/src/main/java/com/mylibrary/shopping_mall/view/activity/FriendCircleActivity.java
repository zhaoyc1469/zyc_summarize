package com.mylibrary.shopping_mall.view.activity;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.app.frame.base.BaseActivity;
import com.common.function.utils.KeyboardControlManager;
import com.mylibrary.shopping_mall.BR;
import com.mylibrary.shopping_mall.R;
import com.mylibrary.shopping_mall.databinding.ActivityFriendCircleBinding;
import com.mylibrary.shopping_mall.viewModel.FriendCircleViewModel;

@Route(path = "/test/FriendCircleActivity")
public class FriendCircleActivity extends BaseActivity<ActivityFriendCircleBinding, FriendCircleViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_friend_circle;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }


    @Override
    protected void initViewObservable() {
        //监听键盘
        KeyboardControlManager.observerKeyboardVisibleChange(this, (keyboardHeight, isVisible) -> {

        });
        //结束刷新
        mViewModel.getUIChangeLiveData().getEndRefresh().observe(this, v -> mDataBinding.srlLayout.finishRefresh());
        //结束加载
        mViewModel.getUIChangeLiveData().getEndLoadMore().observe(this, v -> mDataBinding.srlLayout.finishLoadMore());
    }
}