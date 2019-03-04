package com.app.entrance.model;

import com.app.entrance.contract.ILoginViewContract;
import com.app.frame.base.BaseModel;
import com.app.frame.utils.RxUtils;
import com.app.entrance.EntranceApi;

import io.reactivex.functions.Consumer;


public class LoginModel extends BaseModel<ILoginViewContract.ILoginViewModel>{


    public LoginModel(ILoginViewContract.ILoginViewModel mViewModel) {
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
