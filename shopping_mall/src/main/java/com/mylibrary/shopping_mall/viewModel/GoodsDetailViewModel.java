package com.mylibrary.shopping_mall.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.contract.IGoodsDetailViewModel;
import com.mylibrary.shopping_mall.model.GoodsDetailModel;

public class GoodsDetailViewModel extends BaseViewModel<GoodsDetailModel> implements IGoodsDetailViewModel {

    @Override
    protected GoodsDetailModel initModel(IViewModel ViewModel) {
        return new GoodsDetailModel(this);
    }
}
