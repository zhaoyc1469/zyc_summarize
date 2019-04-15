package com.app.entrance.model;

import com.app.entrance.EntranceApi;
import com.app.entrance.contract.ISplashContract;
import com.app.frame.base.BaseModel;
import com.app.frame.https.RetrofitClient;
import com.app.frame.utils.RxUtils;
import com.app.frame.utils.VersionUtils;


public class SplashModel extends BaseModel<ISplashContract.ISplashViewModel> {

    public SplashModel(ISplashContract.ISplashViewModel mViewModel) {
        super(mViewModel);
    }


    public void checkVersion() {
        RetrofitClient.getInstance().createNet(EntranceApi.class)
                .checkVersion(VersionUtils.getAppVersionName())
                .compose(RxUtils.bindToLifecycle(mViewModel.getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(o -> mViewModel.requestFail(""),
                        throwable -> mViewModel.requestFail(""));
    }
}
