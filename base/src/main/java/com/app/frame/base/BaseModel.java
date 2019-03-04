package com.app.frame.base;

import com.app.frame.contract.IModel;
import com.app.frame.contract.IViewModel;
import com.app.frame.https.DataClient;


public class BaseModel<VM extends IViewModel>  implements IModel {

    protected VM mViewModel;
    protected DataClient mDataClient;

    public BaseModel(VM mViewModel) {
        this.mViewModel = mViewModel;
        mDataClient = DataClient.getInstance();
    }

    void destroy() {
        mDataClient = null;
    }
}
