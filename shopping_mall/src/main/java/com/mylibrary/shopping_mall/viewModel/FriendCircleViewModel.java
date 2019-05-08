package com.mylibrary.shopping_mall.viewModel;

import androidx.annotation.NonNull;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.bean.CircleBean;
import com.mylibrary.shopping_mall.contract.IFriendCircleViewModel;
import com.mylibrary.shopping_mall.model.FriendCircleModel;
import com.mylibrary.shopping_mall.view.adapter.FriendCircleAdapter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class FriendCircleViewModel extends BaseViewModel<FriendCircleModel> implements IFriendCircleViewModel {

    @Override
    protected FriendCircleModel initModel(IViewModel ViewModel) {
        return new FriendCircleModel(this);
    }

    @Override
    protected void initData() {
        mModel.requestList(true, 0, 0);

    }

    public FriendCircleAdapter friendCircleAdapter = new FriendCircleAdapter();

    //刷新监听
    public OnRefreshListener onRefreshListener = refreshLayout -> mModel.requestList(true, 0, 0);
    //加载更多监听
    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> mModel.requestList(false, 0, 0);

    @Override
    public void onDestroy() {
        super.onDestroy();
        onRefreshListener = null;
        onLoadMoreListener = null;
        friendCircleAdapter = null;
    }

    @Override
    public void loadListSuccess(boolean reLoad, List<CircleBean.DataBean> list) {
        if (reLoad) {
            friendCircleAdapter.setData(list);
            friendCircleAdapter.notifyDataSetChanged();
            uiChangeLiveData.getEndRefresh().postValue(null);
        } else {
            friendCircleAdapter.setData(list);
            friendCircleAdapter.notifyDataSetChanged();
            uiChangeLiveData.getEndLoadMore().postValue(null);
        }
    }
}
