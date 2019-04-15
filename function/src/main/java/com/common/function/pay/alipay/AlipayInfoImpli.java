/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2017, King, china
**                          All Rights Reserved
**                                
**                              By(King)
**                         
**------------------------------------------------------------------------------
*/
package com.common.function.pay.alipay;


import com.common.function.pay.easypay.base.IPayInfo;

/**
 * 描述 ：包含支付宝支付类型和支付信息
 */
public class AlipayInfoImpli implements IPayInfo {

    private String orderInfo;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

}
