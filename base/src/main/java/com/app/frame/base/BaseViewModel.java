package com.app.frame.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.app.frame.contract.IView;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.app.frame.bus.event.SingleLiveEvent;
import com.app.frame.contract.IViewModel;

import java.util.HashMap;
import java.util.Map;


public class BaseViewModel<M extends BaseModel> extends ViewModel implements IViewModel {

    protected M mModel;
    private LifecycleProvider lifecycle;
    protected UIChangeLiveData uiChangeLiveData;


    void injectLifecycleProvider(IView mView) {
        lifecycle = mView.getLifecycleProvider();
        mModel = initModel(this);
        initData();
    }

    protected void initData() {

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
     * @param url 所跳转的目的 ARouter t地址
     */
    public void startActivity(String url) {
        uiChangeLiveData.startARouterUrl.postValue(url);
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
        private SingleLiveEvent<String> startARouterUrl;
        private SingleLiveEvent<Void> dismissDialogEvent;
        private SingleLiveEvent<Map<String, Object>> startActivityEvent;
        private SingleLiveEvent<Void> finishEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;

        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<String> getStartARountUrl() {
            return startARouterUrl = createLiveData(startARouterUrl);
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
        public static String BUNDLE = "BUNDLE";
    }
}
