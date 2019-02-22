package com.app.frame.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.app.frame.manager.AppManager;


public class BaseActCallbacks implements Application.ActivityLifecycleCallbacks {

    BaseActCallbacks() {
        AppManager.init();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        AppManager.getAppManager().addResumed();
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        AppManager.getAppManager().addStopped();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.getAppManager().removeActivity(activity);
    }
}
