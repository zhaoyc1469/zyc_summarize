package com.app.frame.contract;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface IView {
    LifecycleProvider getLifecycleProvider();
}
