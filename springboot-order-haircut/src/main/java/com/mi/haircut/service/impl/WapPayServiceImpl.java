package com.mi.haircut.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.mi.haircut.config.AlipayConfig;
//import com.mi.haircut.service.WapPayService;
import com.mi.haircut.domain.Order;
import com.mi.haircut.enums.PayStatusEnum;
import com.mi.haircut.mapper.AppointOrderMapper;
import com.mi.haircut.service.WapPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author : shirayner
 * @date : 2018/3/12
 * @time : 11:02
 * @desc :
 **/
@Service
@Slf4j
public class WapPayServiceImpl implements WapPayService {

    @Autowired
    private AppointOrderMapper orderMapper;

    @Override
    public int updateInfo(String orderId) {

        Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order != null){
                log.info("[return支付订单修改状态....]");
                order.setPayStatus(PayStatusEnum.SUCCESS.getCode());
                return orderMapper.updateByPrimaryKey(order);
            }
            return 0;
    }
}
