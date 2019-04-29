package com.mylibrary.shopping_mall.viewModel;

import androidx.annotation.NonNull;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.contract.IGoodsListViewModel;
import com.mylibrary.shopping_mall.model.GoodsListModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class GoodsListViewModel extends BaseViewModel<GoodsListModel> implements IGoodsListViewModel {

    @Override
    protected GoodsListModel initModel(IViewModel ViewModel) {
        return new GoodsListModel(this);
    }

    public void setSrlView(SmartRefreshLayout srlGoods) {
        srlGoods.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {


            }
        });
    }
}
