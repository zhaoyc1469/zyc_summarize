package com.app.frame.base;

import com.app.frame.contract.IModel;
import com.app.frame.contract.IViewModel;
import com.app.frame.https.RetrofitClient;


public class BaseModel<VM extends IViewModel>  implements IModel {

    protected VM mViewModel;
    protected RetrofitClient mRetrofitClient;

    public BaseModel(VM mViewModel) {
        mRetrofitClient = RetrofitClient.getInstance();
        this.mViewModel = mViewModel;
    }

    void destroy() {
        mRetrofitClient = null;
    }
}
