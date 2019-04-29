package com.app.frame.base;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;


import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.frame.contract.IView;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BaseActivity<DataBinding extends ViewDataBinding, ViewModel extends BaseViewModel> extends RxAppCompatActivity implements IView {

    public DataBinding mDataBinding;
    public ViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面接受的参数方法
        initParam();
        //私有的初始化DataBinding和ViewModel方法
        initDataBinding(savedInstanceState);
        //注册ViewModel与View的契约UI回调事件
        registerUIChangeLiveDataCallBack();
        //初始化数据方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
        //注册监听
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
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private void initDataBinding(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
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
        return this;
    }

    private void registerUIChangeLiveDataCallBack() {

        //加载对话框显示
        mViewModel.getUIChangeLiveData().getShowDialogEvent().observe(this, (Observer<String>) title -> {

        });
        //跳转地址
        mViewModel.getUIChangeLiveData().getStartARountUrl().observe(this, (Observer<String>) url ->
                ARouter.getInstance().build(url).navigation(this));
        //加载对话框消失
        mViewModel.getUIChangeLiveData().getDismissDialogEvent().observe(this, (Observer<Void>) v -> {

        });
        //跳入新页面
        mViewModel.getUIChangeLiveData().getStartActivityEvent().observe(this, (Observer<Map<String, Object>>) params -> {
            Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
            Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
            startActivity(clz, bundle);
        });
        //关闭界面
        mViewModel.getUIChangeLiveData().getFinishEvent().observe(this, (Observer<Void>) v -> finish());
        //关闭上一层
        mViewModel.getUIChangeLiveData().getOnBackPressedEvent().observe(this, (Observer<Void>) v -> onBackPressed());
    }

    public <T extends BaseViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
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

    protected abstract int initVariableId();

}
