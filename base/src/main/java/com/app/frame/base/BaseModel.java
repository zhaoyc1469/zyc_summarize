package com.app.frame.base;

import com.app.frame.contract.IModel;
import com.app.frame.https.DataClient;


public class BaseModel implements IModel {

    protected DataClient mDataClient;

    public BaseModel() {
        mDataClient = DataClient.getInstance();
    }

    void destroy() {
        mDataClient = null;
    }
}
