package com.camera.moments.view.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.common.function.utils.KeyboardControlManager;
import com.camera.moments.BR;
import com.camera.moments.R;
import com.camera.moments.bean.MomentsViewBean;
import com.camera.moments.databinding.ActivityMomentsMainBinding;
import com.camera.moments.utils.MomentsViewHelper;
import com.camera.moments.viewModel.MomentsMainViewModel;

@Route(path = "/moments/MomentsMainActivity")
public class MomentsMainActivity extends BaseActivity<ActivityMomentsMainBinding, MomentsMainViewModel> {


    private MomentsViewHelper mViewHelper;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_moments_main;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getUIChangeLiveData().getEndRefresh().observe(this,
                Void -> mDataBinding.srlContent.finishRefresh());
        mViewModel.getUIChangeLiveData().getEndLoadMore().observe(this,
                Void -> mDataBinding.srlContent.finishLoadMore());
        mViewModel.getMomentsLiveData().getShowCommentEvent().observe(this,
                this::showCommentBox);
    }

    @Override
    protected void initUI() {
        if (mViewHelper == null) {
            mViewHelper = new MomentsViewHelper(this);
        }

        //观察键盘弹出与消退
        KeyboardControlManager.observerKeyboardVisibleChange(this, new KeyboardControlManager.OnKeyboardStateChangeListener() {
            View anchorView;

            @Override
            public void onKeyboardChange(int keyboardHeight, boolean isVisible) {
                int commentType = mDataBinding.widgetComment.getCommentType();
                if (isVisible) {
                    anchorView = mViewHelper.alignCommentBoxToView(mDataBinding.rvMoments, mDataBinding.widgetComment, commentType);
                } else {
                    mDataBinding.widgetComment.dismissCommentBox(false);
                    mViewHelper.alignCommentBoxToViewWhenDismiss(mDataBinding.rvMoments, mDataBinding.widgetComment, commentType, anchorView);
                }
            }
        });
    }


    public void showCommentBox(MomentsViewBean momentsViewBean) {
        if (momentsViewBean.getViewHolderRootView() != null) {
            mViewHelper.setCommentAnchorView(momentsViewBean.getViewHolderRootView());
        } else if (momentsViewBean.getCommentWidget() != null) {
            mViewHelper.setCommentAnchorView(momentsViewBean.getCommentWidget());
        }
        mViewHelper.setCommentItemDataPosition(momentsViewBean.getItemPos());
        mDataBinding.widgetComment.toggleCommentBox(momentsViewBean.getMomentid(), momentsViewBean.getCommentWidget()
                == null ? null : momentsViewBean.getCommentWidget().getData(), false);
    }

}