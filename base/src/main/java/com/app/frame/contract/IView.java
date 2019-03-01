package com.app.frame.contract;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface IView {

    LifecycleProvider getLifecycleProvider();

    Activity getTheActivity();
}
