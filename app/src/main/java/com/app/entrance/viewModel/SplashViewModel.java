package com.app.entrance.viewModel;

import androidx.databinding.ObservableField;

import com.app.entrance.contract.ISplashViewModel;
import com.app.frame.socket.MainSocketService;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;
import com.app.entrance.model.SplashModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

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
        //初始化Socket
        MainSocketService.getInstance().connect();

        skipDisposable = Flowable.intervalRange(0, 2, 0, 1, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(i -> skipNum.set(String.valueOf(3 - i)))
                .doOnComplete(this::skipAdvertising)
                .subscribe();

    }


    private void skipAdvertising() {
        if (skipDisposable != null) {
            skipDisposable.dispose();
        }
        startActivity("/app/LoginActivity");
    }
}
