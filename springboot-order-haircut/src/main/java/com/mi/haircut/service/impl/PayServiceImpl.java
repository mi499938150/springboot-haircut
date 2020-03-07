package com.mi.haircut.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointOrderService;
import com.mi.haircut.service.PayService;
import com.mi.haircut.utils.JsonUtil;
import com.mi.haircut.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Rong
 * @date : 2020/1/30
 * @Desc:
 */
@Service("payService")
@Slf4j
public class PayServiceImpl  implements PayService {


    private static final String ORDER_NAME = "微信预约";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private AppointOrderService orderService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public PayResponse create(OrderDTO orderDTO) {

//        Appointment appointment = appointmentMapper.selectByPrimaryKey(orderDTO.getAppointId());

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getCustomerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);

        log.info("[微信支付]发起支付,request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse =  bestPayService.pay(payRequest);
        log.info("[微信支付]发起支付,response ={}",JsonUtil.toJson(payRequest));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notify) {
        //1.验证签名

        //2.支付的状态

        //3.支付金额

        //4.支付人(下单人 == 支付人)

        PayResponse payResponse =  bestPayService.asyncNotify(notify);
        log.info("[微信支付]异步通知,payResponse={}",JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if (orderDTO == null){
            log.error("[微信支付]异步通知,订单不存在,orderId={}",payResponse.getOrderId());
            throw new AppointException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致(考虑 0.10 0.1)  注意比较类型
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付]异步通知,订单金额不一致，orderId={},微信通知金额={},系统金额={}"
                    ,payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new AppointException(ResultEnum.WXPAY_NOTIFY_MONEY_ERROR);
        }


        //修改订单支付状态
        orderService.paid(orderDTO);

        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);
        log.info("[微信退款]request={}",JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款]response={}",JsonUtil.toJson(refundResponse));


        return refundResponse;
    }
}