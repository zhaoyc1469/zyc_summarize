package com.app.frame.bus.bean;

import android.app.Activity;
import android.os.Bundle;

public class StartActBean {

    public final static String bundleKey = "bundleKey";

    public StartActBean(String actUrl) {
        this.actUrl = actUrl;
    }

    public StartActBean(String actUrl, Bundle bundle) {
        this.actUrl = actUrl;
        this.bundle = bundle;
    }

    public String actUrl;

    public Bundle bundle;
}
