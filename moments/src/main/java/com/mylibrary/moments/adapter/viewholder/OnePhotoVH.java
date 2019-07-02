package com.mylibrary.moments.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import com.mylibrary.moments.databinding.ItemMomentOnePhotoBinding;
import com.mylibrary.moments.viewModel.MomentItemViewModel;
import com.trello.rxlifecycle3.LifecycleProvider;

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
