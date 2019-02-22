package com.app.frame.contract;

import android.arch.lifecycle.LifecycleObserver;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface IViewModel extends LifecycleObserver {

    LifecycleProvider getLifecycleProvider();

    void requestFail(String reason);
}
