package com.zyc_summarize.base.base;

import android.arch.lifecycle.ViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.zyc_summarize.base.contract.IViewModel;

public class BaseViewModel<M extends BaseModel> extends ViewModel implements IViewModel {

    private LifecycleProvider lifecycle;
    protected M mModel;


    void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = lifecycle;
        mModel = initModel(this);
    }

    @Override
    public LifecycleProvider getLifecycleProvider() {
        return lifecycle;
    }

    protected M initModel(IViewModel ViewModel) {
        return null;
    }

    protected void registerRxBus() {

    }

    protected void removeRxBus() {

    }
}
