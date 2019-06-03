package com.app.frame.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RxClickUtils {
    /**
     * 点击事件
     *
     * @param view
     * @param activity
     * @param consumer
     */
    @SuppressLint("CheckResult")
    public static void setOnClick(View view, RxAppCompatActivity activity, Consumer consumer) {
        RxView.clicks(view)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer,
                        throwable -> throwable.printStackTrace());
    }

    public static Disposable setOnClick(View view, Consumer consumer) {
        return RxView.clicks(view)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable ->
                        throwable.printStackTrace());
    }

}
