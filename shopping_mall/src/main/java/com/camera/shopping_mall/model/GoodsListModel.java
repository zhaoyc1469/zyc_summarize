package com.camera.shopping_mall.model;

import com.app.frame.base.BaseModel;
import com.app.frame.https.RetrofitClient;
import com.app.frame.utils.RxUtils;
import com.camera.shopping_mall.Shop_Api;
import com.camera.shopping_mall.contract.IGoodsListViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class GoodsListModel extends BaseModel<IGoodsListViewModel> {

    public GoodsListModel(IGoodsListViewModel mViewModel) {
        super(mViewModel);
    }


    public void loadGoodsList(boolean isLoadMore) {
        RetrofitClient.getInstance().createNet(Shop_Api.class)
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
                        mViewModel.loadGoodsListSuccess(isLoadMore);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}