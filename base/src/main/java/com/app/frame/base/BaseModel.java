package com.app.frame.base;

import com.app.frame.contract.IModel;
import com.app.frame.contract.IViewModel;
import com.app.frame.https.LocalClient;
import com.app.frame.https.RetrofitClient;


public class BaseModel<VM extends IViewModel>  implements IModel {

    protected VM mViewModel;

    public BaseModel(VM mViewModel) {
        this.mViewModel = mViewModel;
    }

    void destroy() {
        mViewModel = null;
    }

    @Override
    public String getUserName() {
        return LocalClient.getInstance().getUserName();
    }

    @Override
    public String getUserNo() {
        return null;
    }

    @Override
    public String getPassword() {
        return LocalClient.getInstance().getPassword();
    }
}
