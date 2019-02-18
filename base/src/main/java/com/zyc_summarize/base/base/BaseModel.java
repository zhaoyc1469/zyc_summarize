package com.zyc_summarize.base.base;

import com.zyc_summarize.base.contract.IModel;
import com.zyc_summarize.base.https.HttpUtil;


public class BaseModel implements IModel{

    protected HttpUtil mHttpUtil;

    public BaseModel() {
        mHttpUtil = HttpUtil.getInstance();
    }
}
