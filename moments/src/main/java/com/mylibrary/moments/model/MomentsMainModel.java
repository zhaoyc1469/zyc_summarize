package com.mylibrary.moments.model;

import com.app.frame.base.BaseModel;
import com.app.frame.https.RetrofitClient;
import com.app.frame.utils.RxUtils;
import com.mylibrary.moments.Moment_Api;
import com.mylibrary.moments.contract.IMomentsMainViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MomentsMainModel extends BaseModel<IMomentsMainViewModel> {

    public MomentsMainModel(IMomentsMainViewModel mViewModel) {
        super(mViewModel);
    }

    public void loadMomentsList(boolean isLoadMore) {
        RetrofitClient.getInstance().createNet(Moment_Api.class)
                .checkVersion("saf")
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
                        mViewModel.loadMomentsListSuccess(isLoadMore);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}