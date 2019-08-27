package com.camera.shopping_mall.contract;

import com.app.frame.contract.IViewModel;

public interface IGoodsListViewModel extends IViewModel {

    void loadGoodsListSuccess(boolean isLoadMore);
}
