package com.mi.haircut.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.haircut.domain.OrderDetail;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换
 */
@Slf4j
public class OrderFormOrderDtoConverter {

    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCustomerName(orderForm.getName());
        orderDTO.setCustomerPhone(orderForm.getPhone());
        orderDTO.setAppointId(orderForm.getAppointId());
//        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setCustomerOpenid(orderForm.getOpenid());
        orderDTO.setAppointQuantity(orderForm.getAppointQuantity());
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        try {
//            orderDetailList =  gson.fromJson(orderForm.getItems(),
//                    new TypeToken<List<OrderDetail>>() {
//                    }.getType());
//        }catch (Exception e){
//            log.error("[对象转换]错误String={}",orderForm.getItems());
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}