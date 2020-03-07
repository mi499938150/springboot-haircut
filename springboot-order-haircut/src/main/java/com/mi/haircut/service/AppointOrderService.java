package com.mi.haircut.service;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.Vo.OrderDetailInfoVo;
import com.mi.haircut.Vo.OrderDetailVo;
import com.mi.haircut.domain.Order;
import com.mi.haircut.dto.OrderDTO;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/18
 * @Desc: 订单
 */
public interface AppointOrderService {


    /**创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单*/
    OrderDTO findOne(String orderId);

    /**查询订单列表*/
    PageInfo<OrderDTO> findList(String buyerOpenid,Integer offset, Integer pageSize);

    OrderDTO findList(String customerOpenid);

    /**用户查询订单*/
    List<OrderDetailInfoVo> findByoOrderDetailList(String customerOpenid);





    /**
     * 卖家订单
     */

    /**取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);

    /**查询订单列表*/
    PageInfo<OrderDTO> findList(Integer offset, Integer pageSize);

}
