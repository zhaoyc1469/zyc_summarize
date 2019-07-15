package com.app.frame.application;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.frame.BuildConfig;
import com.app.frame.base.ConfigModule;
import com.app.frame.utils.ManifestParserUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class BaseApplication extends MultiDexApplication {

    public final static boolean isDebug = true;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //用反射, 将 AndroidManifest.xml 中带有 ConfigModule 标签的 class 转成对象集合（List<ConfigModule>）
        List<ConfigModule> mModules = new ManifestParserUtils(this).parse();

        //遍历之前获得的集合, 执行每一个 ConfigModule 实现类的某些方法
        for (ConfigModule module : mModules) {
            module.injectionConfiguration(this);
        }

        registerActivityLifecycleCallbacks(new BaseActCallbacks(this));

        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        //初始化路由
        ARouter.init(this);
        //初始化mmkv
        MMKV.initialize(this);
        //注册bugly
        CrashReport.initCrashReport(getApplicationContext(), "214", false);

    }


}
