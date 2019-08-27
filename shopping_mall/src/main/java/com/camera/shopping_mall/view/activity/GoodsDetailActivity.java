package com.camera.shopping_mall.view.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.camera.shopping_mall.BR;
import com.camera.shopping_mall.R;
import com.camera.shopping_mall.databinding.ActivityGoodsDetailBinding;
import com.camera.shopping_mall.viewModel.GoodsDetailViewModel;

@Route(path = "/shop/GoodsDetailActivity")
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