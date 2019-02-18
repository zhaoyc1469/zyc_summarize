package com.zyc_summarize.base.base;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<DataBinding extends ViewDataBinding, ViewModel extends BaseViewModel> extends RxAppCompatActivity {

    public DataBinding mDataBinding;
    public ViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面接受的参数方法
        initParam();
        //私有的初始化DataBinding和ViewModel方法
        initDataBinding(savedInstanceState);
        //注册监听
        mViewModel.registerRxBus();
    }

    protected void initParam() {

    }

    private void initDataBinding(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));

        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (ViewModel) createViewModel(this, modelClass);
        }

        mViewModel.injectLifecycleProvider(this);
    }


    public <T extends android.arch.lifecycle.ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mViewModel);
        if (mViewModel != null) {
            mViewModel.removeRxBus();
        }
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
    }

    public abstract int initContentView(Bundle savedInstanceState);

}
