package com.app.entrance.model;

import com.app.frame.base.BaseModel;
import com.app.frame.utils.RxUtils;
import com.app.entrance.EntranceApi;
import com.app.entrance.contract.ILoginViewModel;

import io.reactivex.functions.Consumer;


public class LoginModel extends BaseModel {

    private ILoginViewModel viewModel;

    public LoginModel(ILoginViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    public void login() {
        mDataClient.createNet(EntranceApi.class)
                .login()
                .compose(RxUtils.bindToLifecycle(viewModel.getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        viewModel.requestFail("");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        viewModel.loginSuccess();
                    }
                });
    }
}
