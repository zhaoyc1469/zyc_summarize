package com.mylibrary.moments.adapter.viewholder;

import androidx.annotation.NonNull;
import com.mylibrary.moments.databinding.ItemMomentHeadBinding;
import com.trello.rxlifecycle3.LifecycleProvider;

public class MomentHeadVH extends BaseMomentVH {

    private LifecycleProvider lifecycleProvider;

    public MomentHeadVH(@NonNull ItemMomentHeadBinding binding, LifecycleProvider lifecycleProvider) {
        super(binding.getRoot());
        this.lifecycleProvider = lifecycleProvider;
    }

}