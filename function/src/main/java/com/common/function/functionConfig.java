package com.common.function;

import android.content.Context;


import com.app.frame.base.ConfigModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class functionConfig implements ConfigModule {


    @Override
    public void injectionConfiguration(Context context) {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context1, layout) -> {
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
            return new ClassicsHeader(context1);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context12, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context12).setDrawableSize(20);
        });
    }
}
