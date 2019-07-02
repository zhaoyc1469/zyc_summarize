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
import com.mylibrary.moments.bean.MomentsInfo;
import com.mylibrary.moments.bean.TextBean;
import com.mylibrary.moments.view.customs.commentwidget.CommentPopup;
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
            textVH.setTextBean(new TextBean());
            textVH.setPopupClickListener(onPopupClickListener);
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


    private CommentPopup.OnPopupClickListener onPopupClickListener = new CommentPopup.OnPopupClickListener() {
        @Override
        public void onLikeClick(View v, @NonNull MomentsInfo info, boolean hasLiked) {
            if (hasLiked) {
            } else {
            }
        }

        @Override
        public void onCommentClick(View v, @NonNull MomentsInfo info) {
        }
    };
}
