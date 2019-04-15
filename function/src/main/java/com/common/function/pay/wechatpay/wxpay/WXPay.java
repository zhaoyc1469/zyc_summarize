package com.common.function.pay.wechatpay.wxpay;

import android.app.Activity;
import android.text.TextUtils;

import com.common.function.pay.easypay.base.IPayStrategy;
import com.common.function.pay.easypay.callback.IPayCallback;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPay implements IPayStrategy<WXPayInfoImpli> {

    private static WXPay mWXPay;
    private WXPayInfoImpli payInfoImpli;
    private static IPayCallback sPayCallback;
    private IWXAPI mWXApi;

    private WXPay(Activity mActivity, String wxAppId) {
        mWXApi = WXAPIFactory.createWXAPI(mActivity.getApplicationContext(), null);
        mWXApi.registerApp(wxAppId);
    }

    public static WXPay getInstance(Activity mActivity, String wxAppId){
        if(mWXPay == null){
            synchronized (WXPay.class){
                if(mWXPay == null) {
                    mWXPay = new WXPay(mActivity,wxAppId);
                }
            }
        }
        return mWXPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    @Override
    public void pay(Activity activity, WXPayInfoImpli payInfo, IPayCallback payCallback) {
        this.payInfoImpli = payInfo;
        sPayCallback = payCallback;
        if(!check()) {
            if(payCallback != null) {
                payCallback.failed();
            }
            return;
        }
        if(payInfoImpli == null || TextUtils.isEmpty(payInfoImpli.getAppid()) || TextUtils.isEmpty(payInfoImpli.getPartnerid())
                || TextUtils.isEmpty(payInfoImpli.getPrepayId()) || TextUtils.isEmpty(payInfoImpli.getPackageValue()) ||
                TextUtils.isEmpty(payInfoImpli.getNonceStr()) || TextUtils.isEmpty(payInfoImpli.getTimestamp()) ||
                TextUtils.isEmpty(payInfoImpli.getSign())) {
            if(payCallback != null) {
                payCallback.failed();
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfoImpli.getAppid();
        req.partnerId = payInfoImpli.getPartnerid();
        req.prepayId = payInfoImpli.getPrepayId();
        req.packageValue = payInfoImpli.getPackageValue();
        req.nonceStr = payInfoImpli.getNonceStr();
        req.timeStamp = payInfoImpli.getTimestamp();
        req.sign = payInfoImpli.getSign();

        mWXApi.sendReq(req);
    }

    //支付回调响应
    public void onResp(int error_code) {
        if(sPayCallback == null) {
            return;
        }

        if(error_code == 0) {   //成功
            sPayCallback.success();
        } else if(error_code == -1) {   //错误
            sPayCallback.failed();
        } else if(error_code == -2) {   //取消
            sPayCallback.cancel();
        }

        sPayCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
