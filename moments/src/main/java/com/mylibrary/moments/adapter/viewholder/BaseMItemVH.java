package com.mylibrary.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.mylibrary.moments.adapter.rvAdapter.MomentsMainAdapter;
import com.mylibrary.moments.bean.MomentsInfo;
import com.mylibrary.moments.bean.TextBean;
import com.mylibrary.moments.view.customs.commentwidget.CommentPopup;
import com.mylibrary.moments.view.customs.commentwidget.CommentWidget;
import com.mylibrary.moments.viewModel.MomentItemViewModel;
import com.trello.rxlifecycle3.LifecycleProvider;

public abstract class BaseMItemVH<T extends ViewDataBinding> extends BaseMomentVH {

    protected MomentItemViewModel momentItemViewModel;
    private CommentPopup commentPopup;
    protected T binding;
    private MomentsMainAdapter.ShowCommentListener listener;

    BaseMItemVH(@NonNull T binding) {
        super(binding.getRoot());
        momentItemViewModel = new MomentItemViewModel(itemView);
        if (commentPopup == null) {
            commentPopup = new CommentPopup(binding.getRoot().getContext());
            CommentPopup.OnPopupClickListener onPopupClickListener = new CommentPopup.OnPopupClickListener() {
                @Override
                public void onLikeClick(View v, @NonNull MomentsInfo info, boolean hasLiked) {
                    if (hasLiked) {
                    } else {
                    }
                }

                @Override
                public void onCommentClick(View v, @NonNull MomentsInfo info) {
                    listener.showCommentBox(itemView, getAdapterPosition(), "213", null);
                }
            };
            commentPopup.setOnCommentPopupClickListener(onPopupClickListener);
        }
        VhMenuClickListener menuClickListener = (itemView, textBean) -> {
            commentPopup.showPopupWindow(getMenuImgView());
        };
        momentItemViewModel.setMenuClickListener(menuClickListener);

        this.binding = binding;
        bindMomentView(momentItemViewModel);
    }

    public void setTextBean(TextBean textBean) {
        momentItemViewModel.setTextBean(textBean);
    }

    public void setPopupClickListener(MomentsMainAdapter.ShowCommentListener listener) {
        this.listener = listener;
    }


    public interface VhMenuClickListener {
        void onMenuClick(View itemView, TextBean textBean);
    }

    public abstract void bindMomentView(MomentItemViewModel momentItemViewModel);

    public abstract View getMenuImgView();


}
