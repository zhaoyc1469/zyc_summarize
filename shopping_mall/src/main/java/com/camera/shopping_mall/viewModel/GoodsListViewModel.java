package com.camera.shopping_mall.viewModel;

import androidx.annotation.NonNull;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.camera.shopping_mall.contract.IGoodsListViewModel;
import com.camera.shopping_mall.model.GoodsListModel;
import com.camera.shopping_mall.adapter.rvAdapter.GoodsListAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class GoodsListViewModel extends BaseViewModel<GoodsListModel> implements IGoodsListViewModel {

    @Override
    protected GoodsListModel initModel(IViewModel ViewModel) {
        return new GoodsListModel(this);
    }

    public GoodsListAdapter goodsListAdapter = new GoodsListAdapter();

    //刷新监听
    public OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mModel.loadGoodsList(true);
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mModel.loadGoodsList(false);
        }
    };


    @Override
    public void loadGoodsListSuccess(boolean isLoadMore) {
        if (isLoadMore) {
            getUIChangeLiveData().getEndLoadMore().postValue(null);
        } else {
            getUIChangeLiveData().getEndRefresh().postValue(null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onRefreshLoadMoreListener = null;
        goodsListAdapter = null;
    }
}
