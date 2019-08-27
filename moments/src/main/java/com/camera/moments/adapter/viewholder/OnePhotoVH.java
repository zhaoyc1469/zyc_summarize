package com.camera.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import com.camera.moments.databinding.ItemMomentOnePhotoBinding;
import com.camera.moments.viewModel.MomentItemViewModel;

public class OnePhotoVH extends BaseMItemVH<ItemMomentOnePhotoBinding> {


    public OnePhotoVH(@NonNull ItemMomentOnePhotoBinding binding) {
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
