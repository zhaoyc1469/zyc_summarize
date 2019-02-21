package com.zyc_summarize.base.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BaseActivity<DataBinding extends ViewDataBinding, ViewModel extends BaseViewModel> extends RxAppCompatActivity {

    public DataBinding mDataBinding;
    public ViewModel mViewModel;
    private int viewModelId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面接受的参数方法
        initParam();
        //私有的初始化DataBinding和ViewModel方法
        initDataBinding(savedInstanceState);
        //注册ViewModel与View的契约UI回调事件
        registerUIChangeLiveDataCallBack();
        initData();
        //注册监听
        mViewModel.registerRxBus();
    }

    protected void initData() {

    }

    protected void initParam() {

    }

    private void initDataBinding(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        viewModelId = initVariableId();
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

        mViewModel.injectLifecycleProvider(this);
    }

    private void registerUIChangeLiveDataCallBack() {

        //加载对话框显示
        mViewModel.getUIChangeLiveData().getShowDialogEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String title) {

            }
        });
        //加载对话框消失
        mViewModel.getUIChangeLiveData().getDismissDialogEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {

            }
        });
        //跳入新页面
        mViewModel.getUIChangeLiveData().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {

            }
        });
        //关闭界面
        mViewModel.getUIChangeLiveData().getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
        //关闭上一层
        mViewModel.getUIChangeLiveData().getOnBackPressedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                onBackPressed();
            }
        });
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

    protected abstract int initVariableId();

}
