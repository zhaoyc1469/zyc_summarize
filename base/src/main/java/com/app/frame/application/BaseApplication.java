package com.app.frame.application;

import android.support.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new BaseActCallbacks());
    }
}
