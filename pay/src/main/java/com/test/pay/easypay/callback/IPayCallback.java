package com.test.pay.easypay.callback;

public interface IPayCallback {
    void success();
    void failed();
    void cancel();
}
