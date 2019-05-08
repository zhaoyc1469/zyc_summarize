package com.mylibrary.shopping_mall.contract;

import com.app.frame.contract.IViewModel;
import com.mylibrary.shopping_mall.bean.CircleBean;

import java.util.List;

public interface IFriendCircleViewModel extends IViewModel {

    void loadListSuccess(boolean reLoad, List<CircleBean.DataBean> list);
}
