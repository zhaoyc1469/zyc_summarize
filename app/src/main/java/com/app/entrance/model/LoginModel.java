package com.app.entrance.model;

import com.app.entrance.contract.ILoginContract;
import com.app.frame.base.BaseModel;
import com.app.frame.utils.RxUtils;
import com.app.entrance.EntranceApi;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class LoginModel extends BaseModel<ILoginContract.ILoginViewModel>{


    public LoginModel(ILoginContract.ILoginViewModel mViewModel) {
        super(mViewModel);
    }

    public void login() {
        mRetrofitClient.createNet(EntranceApi.class)
                .login()
                .compose(RxUtils.bindToLifecycle(mViewModel.getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.loginSuccess();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
