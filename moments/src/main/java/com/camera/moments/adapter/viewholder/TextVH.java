package com.camera.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.camera.moments.databinding.ItemMomentTextBinding;
import com.camera.moments.viewModel.MomentItemViewModel;

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
