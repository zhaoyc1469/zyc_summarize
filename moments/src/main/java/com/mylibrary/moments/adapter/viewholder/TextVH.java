package com.mylibrary.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.mylibrary.moments.databinding.ItemMomentTextBinding;
import com.mylibrary.moments.viewModel.MomentItemViewModel;

public class TextVH extends BaseMItemVH<ItemMomentTextBinding> {

    public TextVH(@NonNull ItemMomentTextBinding binding) {
        super(binding);
    }

    @Override
    public void bindMomentView(MomentItemViewModel momentItemViewModel) {
        binding.setViewModel(momentItemViewModel);
    }

    @Override
    public View getMenuImgView() {
        return binding.inclPraise.ivPraiseComment;
    }

}
