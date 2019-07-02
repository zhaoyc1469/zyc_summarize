package com.mylibrary.moments.contract;

import com.app.frame.contract.IViewModel;

public interface IMomentsMainViewModel extends IViewModel {

    void loadMomentsListSuccess(boolean isLoadMore);
}
