package com.app.frame.utils;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    /**
     * 生命周期绑定
     *
     * @param lifecycle Fragment
     */
    public static LifecycleTransformer bindToLifecycle(@NonNull LifecycleProvider lifecycle) {
        return lifecycle.bindToLifecycle();
    }

    /**
     * 线程调度器
     */
    public static ObservableTransformer schedulersTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.from(ThreadPoolTools.getInstance().getExecutorService()))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
