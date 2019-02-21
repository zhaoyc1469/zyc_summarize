package com.zyc_summarize.base.base;

import com.zyc_summarize.base.contract.IModel;
import com.zyc_summarize.base.https.DataClient;


public class BaseModel implements IModel {

    protected DataClient mDataClient;

    public BaseModel() {
        mDataClient = DataClient.getInstance();
    }

    void destroy() {
        mDataClient = null;
    }
}
