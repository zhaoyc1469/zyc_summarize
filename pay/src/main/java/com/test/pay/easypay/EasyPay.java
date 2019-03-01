package com.test.pay.easypay;

import android.app.Activity;

import com.test.pay.easypay.base.IPayInfo;
import com.test.pay.easypay.base.IPayStrategy;
import com.test.pay.easypay.callback.IPayCallback;

/**
 * 调用 : 实例化支付策略payway,以及支付订单信息，作为参数直接传入。
 *      使用方法1：调用EasyPay.pay()方法即可。
 *      使用方法2：实例化payStrategy,直接调用其pay方法。如：new Alipay().pay(...)
 */
public class EasyPay {
    public static <T extends IPayInfo> void pay(IPayStrategy<T> payWay, Activity mActivity, T payinfo, IPayCallback callback){
        payWay.pay(mActivity, payinfo, callback);
    }
}
