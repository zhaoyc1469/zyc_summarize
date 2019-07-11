package com.mylibrary.moments.viewModel;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.app.frame.base.BaseViewModel;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;
import com.mylibrary.moments.bean.MomentsInfo;
import com.mylibrary.moments.bean.MomentsViewBean;
import com.mylibrary.moments.contract.IMomentsMainViewModel;
import com.mylibrary.moments.model.MomentsMainModel;
import com.mylibrary.moments.adapter.rvAdapter.MomentsMainAdapter;
import com.mylibrary.moments.utils.MomentsLiveData;
import com.mylibrary.moments.utils.MomentsViewHelper;
import com.mylibrary.moments.view.customs.commentwidget.CommentBox;
import com.mylibrary.moments.view.customs.commentwidget.CommentPopup;
import com.mylibrary.moments.view.customs.commentwidget.CommentWidget;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.Map;

public class MomentsMainViewModel extends BaseViewModel<MomentsMainModel> implements IMomentsMainViewModel {

    private MomentsLiveData momentsLiveData;

    @Override
    protected MomentsMainModel initModel(IViewModel ViewModel) {
        return new MomentsMainModel(this);
    }

    public MomentsMainAdapter goodsListAdapter = new MomentsMainAdapter(this);

    public MomentsLiveData getMomentsLiveData() {
        if (momentsLiveData == null) {
            momentsLiveData = new MomentsLiveData();
        }
        return momentsLiveData;
    }

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
//
//    //弹出评论框
//    public void showCommentBox(View v, MomentsInfo info) {IComment comment = widget.getData();
//        CommentInfo commentInfo = null;
//        if (comment instanceof CommentInfo) {
//            commentInfo = (CommentInfo) comment;
//        }
//        if (commentInfo == null) return;
//        if (commentInfo.canDelete()) {
//            deleteCommentPopup.showPopupWindow(commentInfo);
//        } else {
//            momentPresenter.showCommentBox(null, itemPosition, momentsInfo.getMomentid(), widget);
//        }
////        if (viewHolderRootView != null) {
////            mViewHelper.setCommentAnchorView(viewHolderRootView);
////        } else if (commentWidget != null) {
////            mViewHelper.setCommentAnchorView(commentWidget);
////        }
////        mViewHelper.setCommentItemDataPosition(itemPos);
//
//    }

    //点赞
    public void praiseMoment() {

    }

    public void showCommentBox(View itemView, int itemPosition, String momentid, CommentWidget commentWidget) {
        MomentsViewBean momentsViewBean = new MomentsViewBean();
        momentsViewBean.setViewHolderRootView(itemView);
        momentsViewBean.setItemPos(itemPosition);
        momentsViewBean.setMomentid(momentid);
        momentsViewBean.setCommentWidget(commentWidget);
        momentsLiveData.getShowCommentEvent().postValue(momentsViewBean);
    }
}
