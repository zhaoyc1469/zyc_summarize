package com.common.function.pay.easypay.callback;

public interface IPayCallback {
    void success();
    void failed();
    void cancel();
}
