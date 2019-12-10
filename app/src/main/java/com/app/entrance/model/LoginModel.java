package com.app.entrance.model;

import android.text.TextUtils;

import com.app.entrance.contract.ILoginViewModel;
import com.app.frame.base.BaseModel;
import com.app.frame.https.LocalClient;
import com.app.frame.socket.MainSocketService;


public class LoginModel extends BaseModel<ILoginViewModel> {


    public LoginModel(ILoginViewModel mViewModel) {
        super(mViewModel);
    }

    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            MainSocketService.getInstance().writeString("login,188888888,123456");
        } else {
            MainSocketService.getInstance().writeString("login," + username + "," + password);
        }

//        RetrofitClient.getInstance().createNet(EntranceApi.class)
//                .login()
//                .compose(RxUtils.bindToLifecycle(mViewModel.getLifecycleProvider()))
//                .compose(RxUtils.schedulersTransformer())
//                .subscribe(new Observer() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mViewModel.loginSuccess();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


    public void setUserName(String userName) {
        LocalClient.getInstance().setUserName(userName);
    }

    public void setPassword(String password) {
        LocalClient.getInstance().setPassword(password);
    }

    public String getPassword() {
        return "";
    }


}
