package com.app.frame.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.app.frame.bus.bean.StartActBean;
import com.app.frame.contract.IView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;

import java.util.Map;

public class BaseViewModel<V extends IView,M extends BaseModel> extends ViewModel implements IViewModel {

    protected V mView;
    protected M mModel;
    protected UIChangeLiveData uiChangeLiveData;


    void injectLifecycleProvider(V mView) {
        this.mView = mView;
        mModel = initModel(this);
    }

    @Override
    public LifecycleProvider getLifecycleProvider() {
        return mView.getLifecycleProvider();
    }

    @Override
    public void requestFail(String reason) {

    }

    protected M initModel(IViewModel ViewModel) {
        return null;
    }

    protected void registerRxBus() {

    }

    protected void removeRxBus() {

    }

    protected void startActivity(String url) {
        uiChangeLiveData.startActivityEvent.postValue(new StartActBean(url));
    }

    protected void startActivity(String url, Bundle bundle) {
        uiChangeLiveData.startActivityEvent.postValue(new StartActBean(url, bundle));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mModel != null){
            mModel.destroy();
        }
    }

    UIChangeLiveData getUIChangeLiveData() {
        if (uiChangeLiveData == null) {
            uiChangeLiveData = new UIChangeLiveData();
        }
        return uiChangeLiveData;
    }


    public final class UIChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<String> showDialogEvent;
        private SingleLiveEvent<Void> dismissDialogEvent;
        private SingleLiveEvent<StartActBean> startActivityEvent;
        private SingleLiveEvent<Void> finishEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;

        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<Void> getDismissDialogEvent() {
            return dismissDialogEvent = createLiveData(dismissDialogEvent);
        }

        public SingleLiveEvent<StartActBean> getStartActivityEvent() {
            return startActivityEvent = createLiveData(startActivityEvent);
        }

        public SingleLiveEvent<Void> getFinishEvent() {
            return finishEvent = createLiveData(finishEvent);
        }

        public SingleLiveEvent<Void> getOnBackPressedEvent() {
            return onBackPressedEvent = createLiveData(onBackPressedEvent);
        }

        private SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent();
            }
            return liveData;
        }

        @Override
        public void observe(LifecycleOwner owner, Observer observer) {
            super.observe(owner, observer);
        }
    }
}
