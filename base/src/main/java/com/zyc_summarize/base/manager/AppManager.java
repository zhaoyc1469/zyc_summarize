package com.zyc_summarize.base.manager;


import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class AppManager {

    /**
     * 管理所有存活的 Activity, 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
     */
    private List<Activity> mActivityList;
    private static volatile AppManager sAppManager;

    private long resumed;
    private long stopped;

    private AppManager() {
        resumed = 0;
        stopped = 0;
    }

    public static void init() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
    }


    public static AppManager getAppManager() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }


    /**
     * 返回app是否在后台运行
     */
    public boolean isAppBackground() {
        return resumed <= stopped;
    }

    /**
     * 返回一个存储所有未销毁的 {@link Activity} 的集合
     *
     * @return List
     */
    private List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    /**
     * 添加 {@link Activity} 到集合
     */
    public void addActivity(Activity activity) {
        synchronized (AppManager.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        }
    }


    /**
     * 删除集合里的指定的 {@link Activity} 实例
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (AppManager.class) {
            mActivityList.remove(activity);
        }
    }

    public void addResumed() {
        ++resumed;
    }

    public void addStopped() {
        ++stopped;
    }
}
