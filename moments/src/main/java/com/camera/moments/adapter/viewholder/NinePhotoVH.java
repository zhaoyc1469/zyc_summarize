package com.camera.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.camera.moments.databinding.ItemMomentNinePhotoBinding;
import com.camera.moments.viewModel.MomentItemViewModel;

public class NinePhotoVH extends BaseMItemVH<ItemMomentNinePhotoBinding> {

    public NinePhotoVH(@NonNull ItemMomentNinePhotoBinding binding) {
        super(binding);
    }

    @Override
    public void bindMomentView(MomentItemViewModel momentItemViewModel) {
        binding.setViewModel(momentItemViewModel);
    }

    @Override
    public View getMenuImgView() {
        return null;
    }
}
