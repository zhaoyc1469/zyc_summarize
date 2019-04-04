package com.app.frame.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTools {

    private BlockingQueue<Runnable> workQueue;
    private ExecutorService executorService;

    private static class ThreadPoolToolsHolder {
        private static final ThreadPoolTools INSTANCE = new ThreadPoolTools();
    }

    private ThreadPoolTools() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        int keepAliveTime = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        workQueue = new LinkedBlockingQueue<Runnable>();
        executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static final ThreadPoolTools getInstance() {
        return ThreadPoolToolsHolder.INSTANCE;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
