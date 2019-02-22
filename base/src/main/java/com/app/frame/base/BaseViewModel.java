package com.app.frame.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.app.frame.bus.Event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;

import java.util.Map;

public class BaseViewModel<M extends BaseModel> extends ViewModel implements IViewModel {

    private LifecycleProvider lifecycle;
    protected M mModel;
    protected UIChangeLiveData uiChangeLiveData;


    void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = lifecycle;
        mModel = initModel(this);
    }

    @Override
    public LifecycleProvider getLifecycleProvider() {
        return lifecycle;
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
        private SingleLiveEvent<Map<String, Object>> startActivityEvent;
        private SingleLiveEvent<Void> finishEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;

        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<Void> getDismissDialogEvent() {
            return dismissDialogEvent = createLiveData(dismissDialogEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
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
