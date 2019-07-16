package com.mylibrary.moments.adapter.rvAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mylibrary.moments.R;
import com.mylibrary.moments.adapter.viewholder.BaseMomentVH;
import com.mylibrary.moments.adapter.viewholder.MomentHeadVH;
import com.mylibrary.moments.adapter.viewholder.NinePhotoVH;
import com.mylibrary.moments.adapter.viewholder.OnePhotoVH;
import com.mylibrary.moments.adapter.viewholder.TextVH;
import com.mylibrary.moments.bean.MomentItemBean;
import com.mylibrary.moments.view.customs.commentwidget.CommentWidget;
import com.mylibrary.moments.viewModel.MomentsMainViewModel;

public class MomentsMainAdapter extends RecyclerView.Adapter<BaseMomentVH> {


    private MomentsMainViewModel mainViewModel;

    public MomentsMainAdapter(MomentsMainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public BaseMomentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new MomentHeadVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_moment_head, parent, false), mainViewModel.getLifecycleProvider());
            case 2:
                return new OnePhotoVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_moment_one_photo, parent, false));
            case 3:
                return new NinePhotoVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_moment_nine_photo, parent, false));
            default:
                return new TextVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_moment_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseMomentVH holder, int position) {
        int momentType = holder.getItemViewType();
        if (momentType == 5) {
            TextVH textVH = (TextVH) holder;
            textVH.setMomentItemBean(new MomentItemBean());
            textVH.setPopupClickListener(onShowCommentListener);
        } else {

        }
    }

    @Override
    public int getItemViewType(int position) {
        return 5;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public interface ShowCommentListener {
        void showCommentBox(View itemView, int itemPosition, String momentid, CommentWidget commentWidget);
    }

    private ShowCommentListener onShowCommentListener = new ShowCommentListener() {
        @Override
        public void showCommentBox(View itemView, int itemPosition, String momentid, CommentWidget commentWidget) {
            mainViewModel.showCommentBox( itemView,  itemPosition,  momentid,  commentWidget);
        }
    };

}
