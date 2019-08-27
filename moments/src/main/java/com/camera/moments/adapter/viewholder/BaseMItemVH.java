package com.camera.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.camera.moments.adapter.rvAdapter.MomentsMainAdapter;
import com.camera.moments.bean.MomentItemBean;
import com.camera.moments.view.customs.commentwidget.CommentPopup;
import com.camera.moments.viewModel.MomentItemViewModel;

public abstract class BaseMItemVH<T extends ViewDataBinding> extends BaseMomentVH {

    protected MomentItemViewModel momentItemViewModel;
    private CommentPopup commentPopup;
    protected T binding;
    private MomentsMainAdapter.ShowCommentListener listener;

    BaseMItemVH(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
        momentItemViewModel = new MomentItemViewModel(itemView);
        initCommentPopup();
        bindMomentView(momentItemViewModel);
    }

    /**
     * 初始化弹窗
     */
    private void initCommentPopup() {
        if (commentPopup == null) {
            commentPopup = new CommentPopup(binding.getRoot().getContext());
            CommentPopup.OnPopupClickListener onPopupClickListener = new CommentPopup.OnPopupClickListener() {
                @Override
                public void onLikeClick(View v, @NonNull MomentItemBean info, boolean hasLiked) {
                    if (hasLiked) {
                    } else {
                    }
                }

                @Override
                public void onCommentClick(View v, @NonNull MomentItemBean info) {
                    listener.showCommentBox(itemView, getAdapterPosition(), "213", null);
                }
            };
            commentPopup.setOnCommentPopupClickListener(onPopupClickListener);
        }
        VhMenuClickListener menuClickListener = (itemView, momentItemBean) -> {
            commentPopup.showPopupWindow(getMenuImgView());
        };
        momentItemViewModel.setMenuClickListener(menuClickListener);
    }

    public void setMomentItemBean(MomentItemBean momentItemBean) {
        momentItemViewModel.setMomentItemBean(momentItemBean);
    }

    public void setPopupClickListener(MomentsMainAdapter.ShowCommentListener listener) {
        this.listener = listener;
    }


    public interface VhMenuClickListener {
        void onMenuClick(View itemView, MomentItemBean momentItemBean);
    }

    public abstract void bindMomentView(MomentItemViewModel momentItemViewModel);

    public abstract View getMenuImgView();


}
