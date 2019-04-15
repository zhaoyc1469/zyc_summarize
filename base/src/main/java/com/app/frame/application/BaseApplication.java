package com.app.frame.application;

import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;

public class BaseApplication extends MultiDexApplication {

    public final static boolean isDebug = false;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new BaseActCallbacks(this));

        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        MMKV.initialize(this);

    }


}
