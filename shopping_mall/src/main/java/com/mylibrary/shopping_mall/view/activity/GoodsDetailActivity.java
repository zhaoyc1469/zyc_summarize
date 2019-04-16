package com.mylibrary.shopping_mall.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.shopping_mall.BR;
import com.mylibrary.shopping_mall.R;
import com.mylibrary.shopping_mall.databinding.ActivityGoodsDetailBinding;
import com.mylibrary.shopping_mall.viewModel.GoodsDetailViewModel;

@Route(path = "/test/GoodsDetailActivity")
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding, GoodsDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_goods_detail;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}