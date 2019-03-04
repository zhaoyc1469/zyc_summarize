package com.app.entrance.model;

import com.app.entrance.contract.ILoginContract;
import com.app.frame.base.BaseModel;
import com.app.frame.utils.RxUtils;
import com.app.entrance.EntranceApi;

import io.reactivex.functions.Consumer;


public class LoginModel extends BaseModel<ILoginContract.ILoginViewModel>{


    public LoginModel(ILoginContract.ILoginViewModel mViewModel) {
        super(mViewModel);
    }

    public void login() {
        mDataClient.createNet(EntranceApi.class)
                .login()
                .compose(RxUtils.bindToLifecycle(mViewModel.getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mViewModel.requestFail("");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.loginSuccess();
                    }
                });
    }
}
