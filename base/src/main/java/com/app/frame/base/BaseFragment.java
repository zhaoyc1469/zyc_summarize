package com.app.frame.base;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.frame.contract.IView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BaseFragment <DataBinding extends ViewDataBinding, ViewModel extends BaseViewModel> extends RxFragment implements IView {

    public DataBinding mDataBinding;
    public ViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面接受的参数方法
        initParam();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        return mDataBinding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding();
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack();
        //页面数据初始化方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
        //注册RxBus
        mViewModel.registerRxBus();
    }

    protected void initParam() {

    }

    protected void initData() {

    }

    protected void initViewObservable() {

    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getTheActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private void initViewDataBinding() {
        int viewModelId = initVariableId();
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
        //关联ViewModel
        mDataBinding.setVariable(viewModelId, mViewModel);
        //订阅
        getLifecycle().addObserver(mViewModel);

        mViewModel.injectLifecycleProvider(this);
    }

    @Override
    public LifecycleProvider getLifecycleProvider() {
        return this;
    }

    @Override
    public Activity getTheActivity() {
        return getActivity();
    }

    private void registerUIChangeLiveDataCallBack() {

        //加载对话框显示
        mViewModel.getUIChangeLiveData().getShowDialogEvent().observe(this, (Observer<String>) title -> {
            ARouter.getInstance().build(title)
                    .navigation();
        });
        //加载对话框消失
        mViewModel.getUIChangeLiveData().getDismissDialogEvent().observe(this, (Observer<Void>) v -> {

        });
        //跳入新页面
        mViewModel.getUIChangeLiveData().getStartActivityEvent().observe(this, (Observer<Map<String, Object>>) params -> {
            Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
            Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
            startActivity(clz, bundle);
        });
    }

    public <T extends android.arch.lifecycle.ViewModel> T createViewModel(BaseFragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(mViewModel);
        if (mViewModel != null) {
            mViewModel.removeRxBus();
        }
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
    }

    public abstract int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract int initVariableId();
}
