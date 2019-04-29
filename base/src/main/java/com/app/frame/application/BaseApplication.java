package com.app.frame.application;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.frame.base.ConfigModule;
import com.app.frame.utils.ManifestParserUtils;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class BaseApplication extends MultiDexApplication {

    public final static boolean isDebug = false;


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
        ARouter.init(this);

        MMKV.initialize(this);
    }


}
