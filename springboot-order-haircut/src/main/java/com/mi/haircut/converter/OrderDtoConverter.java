package com.mi.haircut.converter;

import com.mi.haircut.domain.Order;
import com.mi.haircut.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 */
public class OrderDtoConverter {

    public static OrderDTO convert(Order order){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<Order> orderMasters){
      return   orderMasters.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
