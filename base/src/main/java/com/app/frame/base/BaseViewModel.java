package com.app.frame.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.app.frame.contract.IView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;

import java.util.HashMap;
import java.util.Map;


public class BaseViewModel<V extends IView, M extends BaseModel> extends ViewModel implements IViewModel {

    protected V mView;
    protected M mModel;
    private LifecycleProvider lifecycle;
    protected UIChangeLiveData uiChangeLiveData;


    void injectLifecycleProvider(V mView) {
        this.mView = mView;
        lifecycle = mView.getLifecycleProvider();
        mModel = initModel(this);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

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

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        uiChangeLiveData.startActivityEvent.postValue(params);
    }

    protected void finish() {
        uiChangeLiveData.finishEvent.postValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mModel != null) {
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
    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String CANONICAL_NAME = "CANONICAL_NAME";
        public static String BUNDLE = "BUNDLE";
    }
}
