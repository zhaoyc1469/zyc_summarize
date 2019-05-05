package com.mylibrary.shopping_mall.viewModel;

import androidx.annotation.NonNull;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.contract.IGoodsListViewModel;
import com.mylibrary.shopping_mall.model.GoodsListModel;
import com.mylibrary.shopping_mall.view.adapter.GoodsListAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class GoodsListViewModel extends BaseViewModel<GoodsListModel> implements IGoodsListViewModel {

    @Override
    protected GoodsListModel initModel(IViewModel ViewModel) {
        return new GoodsListModel(this);
    }

    public GoodsListAdapter goodsListAdapter = new GoodsListAdapter();

    //刷新列表控件
    private RefreshLayout sRefreshLayout;
    //刷新监听
    public OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            if (sRefreshLayout == null) {
                sRefreshLayout = refreshLayout;
            }
            mModel.loadGoodsList();
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            if (sRefreshLayout == null) {
                sRefreshLayout = refreshLayout;
            }
            mModel.loadGoodsList();
        }
    };


    @Override
    public void loadGoodsListSuccess() {
        sRefreshLayout.finishLoadMore();
        sRefreshLayout.finishRefresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sRefreshLayout = null;
        onRefreshLoadMoreListener = null;
        goodsListAdapter = null;
    }
}
