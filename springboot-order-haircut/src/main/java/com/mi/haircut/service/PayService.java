package com.mi.haircut.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.mi.haircut.dto.OrderDTO;

/**
 * 支付接口
 */
public interface PayService {

    /**
     * 创建支付订单
     * @param orderDTO
     */
    PayResponse create(OrderDTO orderDTO);


    /**
     * 异步通知
     * @param notify
     */
    PayResponse notify(String notify);

    /**
     * 退款
     * @param orderDTO
     */
    RefundResponse refund(OrderDTO orderDTO);
}
