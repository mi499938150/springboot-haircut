package com.mi.haircut.controller;

import com.mi.haircut.Vo.OrderDetailInfoVo;
import com.mi.haircut.Vo.ResultVo;
import com.mi.haircut.converter.OrderFormOrderDtoConverter;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.form.OrderForm;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointOrderService;
import com.mi.haircut.service.CustomerService;
import com.mi.haircut.service.WapPayService;
import com.mi.haircut.utils.ResultVoUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2019/12/19
 * @Desc:
 */
//@CrossOrigin
@RestController
@RequestMapping("/customer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private AppointOrderService orderService;

    @Autowired
    private CustomerService customerService;

    //支付宝支付


    @Autowired
    private AppointmentMapper appointmentMapper;

    //創建訂單
    @RequestMapping( value = "/api/submitOrder",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        log.info("【开始创建订单....】");
        log.info("【获取参数】result = {}",orderForm);
        log.info("[---------]");
        if (bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确,orderForm={}",orderForm);
            throw new AppointException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
       OrderDTO orderDTO =  OrderFormOrderDtoConverter.convert(orderForm);
//        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
//            log.error("[创建订单],购物车不能为空");
//            throw  new AppointException(ResultEnum.CART_EMPTY);
//        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtil.success(map);

    }

    @RequestMapping(value = "/api/list",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultVo<List<OrderDetailInfoVo>>  list(@RequestParam(value = "openid") String openid
                                        ){
        if (StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
            throw  new AppointException(ResultEnum.PARAM_ERROR);

        }
        List<OrderDetailInfoVo> orderDetailList = orderService.findByoOrderDetailList(openid);
        log.info("[查询结果] result = {}",orderDetailList);
        return ResultVoUtil.success(orderDetailList);

    }


    @RequestMapping(value = "/api/orderDetail",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultVo<List<OrderDetailInfoVo>>  orderDetail(@RequestParam(value = "openid") String openid
    ){
        if (StringUtils.isEmpty(openid)){
            System.out.println("openid 为空"+openid);
            log.info("[查询订单列表] openid为空");
           throw  new AppointException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDetailInfoVo> orderDetailList = orderService.findByoOrderDetailList(openid);

        log.info("[查询结果] result = {}",orderDetailList);
        return ResultVoUtil.success(orderDetailList);

    }





    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/api/detail",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultVo<OrderDTO> detail(@RequestParam(value = "openid") String openid,
                                     @RequestParam(value = "orderId") String orderId){
        if (StringUtils.isEmpty(openid) &&StringUtils.isEmpty(orderId)){
            log.error("[查询订单详情] openid为空");
            throw  new AppointException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = customerService.findOrderOne(openid,orderId);
        return ResultVoUtil.success(orderDTO);
    }

    /**
     * 退票
     * @param openid
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/api/cancel",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        log.info("【获取退约信息,result = {}】",openid);
        log.info("【获取订单号信息,orderId = {}】",orderId);
        OrderDTO orderDTO = customerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }



//    @RequestMapping(value = "api/refundOrder",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    public ResultVo RefundOrder(@R){
//
//    }


}