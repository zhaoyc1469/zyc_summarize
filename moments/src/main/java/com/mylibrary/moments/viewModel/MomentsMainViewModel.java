package com.mylibrary.moments.viewModel;

import android.view.View;

import androidx.annotation.NonNull;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.mylibrary.moments.bean.MomentsInfo;
import com.mylibrary.moments.contract.IMomentsMainViewModel;
import com.mylibrary.moments.model.MomentsMainModel;
import com.mylibrary.moments.adapter.rvAdapter.MomentsMainAdapter;
import com.mylibrary.moments.view.customs.commentwidget.CommentPopup;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class MomentsMainViewModel extends BaseViewModel<MomentsMainModel> implements IMomentsMainViewModel {

    @Override
    protected MomentsMainModel initModel(IViewModel ViewModel) {
        return new MomentsMainModel(this);
    }

    public MomentsMainAdapter goodsListAdapter = new MomentsMainAdapter(this);

    //刷新监听
    public OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mModel.loadMomentsList(true);
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mModel.loadMomentsList(false);
        }
    };

    @Override
    public void loadMomentsListSuccess(boolean isLoadMore) {
        if (isLoadMore) {
            getUIChangeLiveData().getEndLoadMore().postValue(null);
        } else {
            getUIChangeLiveData().getEndRefresh().postValue(null);
        }
    }

    //弹出菜单
}
