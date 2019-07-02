package com.app.entrance.viewModel;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.app.entrance.contract.ISplashViewModel;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.app.entrance.model.SplashModel;
import com.app.frame.utils.ThreadPoolTools;
import com.common.function.utils.PingUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends BaseViewModel<SplashModel> implements ISplashViewModel {

    private Disposable skipDisposable;

    @Override
    protected SplashModel initModel(IViewModel ViewModel) {
        return new SplashModel(this);
    }


    //跳过的时间
    public ObservableField<String> skipNum = new ObservableField<>("3");
    //跳过按钮的点击事件
    public BindingCommand skipClickCommand = new BindingCommand(this::skipAdvertising);

    public void initData() {
        mModel.checkVersion();


        skipDisposable = Flowable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(i -> skipNum.set(String.valueOf(3 - i)))
                .doOnComplete(this::skipAdvertising)
                .subscribe();

    }


    private void skipAdvertising() {
        if (skipDisposable != null){
            skipDisposable.dispose();
        }
        startActivity("/app/LoginActivity");
    }
}
