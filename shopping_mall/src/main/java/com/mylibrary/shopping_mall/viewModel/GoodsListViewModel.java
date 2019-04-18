package com.mylibrary.shopping_mall.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.contract.IGoodsListViewModel;
import com.mylibrary.shopping_mall.model.GoodsListModel;

public class GoodsListViewModel extends BaseViewModel<GoodsListModel> implements IGoodsListViewModel {

    @Override
    protected GoodsListModel initModel(IViewModel ViewModel) {
        return new GoodsListModel(this);
    }
}
