package com.zyc_summarize.demo.model;

import com.zyc_summarize.base.base.BaseModel;
import com.zyc_summarize.base.utils.RxUtils;
import com.zyc_summarize.demo.EntranceApi;
import com.zyc_summarize.demo.contract.ILoginViewModel;

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
