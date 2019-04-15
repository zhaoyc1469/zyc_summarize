package com.common.function.pay.easypay.base;

import android.app.Activity;

import com.common.function.pay.easypay.callback.IPayCallback;

/**
 * 描述 ：统一支付接口。策略模式中统一算法接口。
 */
public interface IPayStrategy<T extends IPayInfo> {
    void pay(Activity activity, T payInfo, IPayCallback payCallback);
}
