package com.zyc_summarize.base.contract;

import android.arch.lifecycle.LifecycleObserver;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface IViewModel extends LifecycleObserver {

    LifecycleProvider getLifecycleProvider();
}
