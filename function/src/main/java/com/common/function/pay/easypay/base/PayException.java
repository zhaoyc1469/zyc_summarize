package com.common.function.pay.easypay.base;

public class PayException extends Exception {
    private int mCode;

    public PayException(String message, int code) {
        super(message);
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }
}
