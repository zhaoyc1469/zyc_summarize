package com.zyc_summarize.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity<DataBinding extends ViewDataBinding, ViewModel extends BaseViewModel> extends RxAppCompatActivity {

    public DataBinding mDataBinding;
    public ViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = onCreateDataBindingView(savedInstanceState);
        mViewModel = onCreateViewModel(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public abstract DataBinding onCreateDataBindingView(Bundle savedInstanceState);

    public abstract ViewModel onCreateViewModel(Bundle savedInstanceState);
}
