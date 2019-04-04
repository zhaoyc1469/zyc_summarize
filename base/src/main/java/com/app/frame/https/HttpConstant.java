package com.app.frame.https;

import com.app.frame.application.BaseApplication;

class HttpConstant {

    static String getBaseUrl() {
        if (BaseApplication.isDebug) {
            return DEVELOPER_URL;
        } else {
            return RELEASE_URL;
        }
    }


    private static final String RELEASE_URL = "http://coder.53site.com/AIIM/";

    private static final String DEVELOPER_URL = "http://developer.53site.com/AIIM/";

}
