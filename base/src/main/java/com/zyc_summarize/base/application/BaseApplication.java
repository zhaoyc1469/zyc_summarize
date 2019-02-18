package com.zyc_summarize.base.application;

import android.support.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new BaseActCallbacks());
    }
}
