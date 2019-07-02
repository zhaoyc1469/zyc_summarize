package com.mylibrary.shopping_mall.view.activity;

import android.os.Bundle;


import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.shopping_mall.BR;
import com.mylibrary.shopping_mall.R;
import com.mylibrary.shopping_mall.databinding.ActivityGoodsListBinding;
import com.mylibrary.shopping_mall.viewModel.GoodsListViewModel;

@Route(path = "/shop/GoodsListActivity")
public class GoodsListActivity extends BaseActivity<ActivityGoodsListBinding, GoodsListViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_goods_list;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getUIChangeLiveData().getEndRefresh().observe(this,
                Void -> mDataBinding.srlContent.finishRefresh());
        mViewModel.getUIChangeLiveData().getEndLoadMore().observe(this,
                Void -> mDataBinding.srlContent.finishLoadMore());
    }
}