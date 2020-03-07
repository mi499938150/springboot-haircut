package com.mi.haircut.service;

import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.mi.haircut.domain.Order;

import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2020/1/26
 * @Desc:
 */
public interface WapPayService {

    /**
     * 用户支付成功
     * @param orderId   修改订单状态
      * @return
     */
    int updateInfo(String orderId);
}