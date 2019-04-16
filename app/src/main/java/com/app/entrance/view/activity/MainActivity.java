package com.app.entrance.view.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.widget.Toast;

import com.app.entrance.viewModel.MainViewModel;
import com.app.frame.base.BaseActivity;
import com.app.entrance.BR;
import com.app.entrance.R;
import com.app.entrance.databinding.ActivityMainBinding;
import com.common.function.pay.alipay.AliPay;
import com.common.function.pay.alipay.AlipayInfoImpli;
import com.common.function.pay.easypay.EasyPay;
import com.common.function.pay.easypay.callback.IPayCallback;
import com.common.function.pay.wechatpay.wxpay.WXPay;
import com.common.function.pay.wechatpay.wxpay.WXPayInfoImpli;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {

        mViewModel.getBottomClickEvent().observe(this, position -> {
            Toast.makeText(this,"215432512", Toast.LENGTH_SHORT).show();
        });
    }






//    private void wxpay() {
//        //实例化微信支付策略
//        String wxAppId = "";
//        WXPay wxPay = WXPay.getInstance(this, wxAppId);
//        //构造微信订单实体。一般都是由服务端直接返回。
//        WXPayInfoImpli wxPayInfoImpli = new WXPayInfoImpli();
//        wxPayInfoImpli.setTimestamp("");
//        wxPayInfoImpli.setSign("");
//        wxPayInfoImpli.setPrepayId("");
//        wxPayInfoImpli.setPartnerid("");
//        wxPayInfoImpli.setAppid("");
//        wxPayInfoImpli.setNonceStr("");
//        wxPayInfoImpli.setPackageValue("");
//        //策略场景类调起支付方法开始支付，以及接收回调。
//        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
//            @Override
//            public void success() {
//            }
//
//            @Override
//            public void failed() {
//            }
//
//            @Override
//            public void cancel() {
//            }
//        });
//    }
//
//    private void alipay() {
//        //实例化支付宝支付策略
//        AliPay aliPay = new AliPay();
//        //构造支付宝订单实体。一般都是由服务端直接返回。
//        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
//        alipayInfoImpli.setOrderInfo("");
//        //策略场景类调起支付方法开始支付，以及接收回调。
//        EasyPay.pay(aliPay, this, alipayInfoImpli, new IPayCallback() {
//            @Override
//            public void success() {
//            }
//
//            @Override
//            public void failed() {
//            }
//
//            @Override
//            public void cancel() {
//            }
//        });
//    }
}
