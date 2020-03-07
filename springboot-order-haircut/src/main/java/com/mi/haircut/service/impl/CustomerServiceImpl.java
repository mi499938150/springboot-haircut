package com.mi.haircut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.WeChatUser;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.WechatUserMapper;
import com.mi.haircut.service.AppointOrderService;
import com.mi.haircut.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/1/4
 * @Desc:
 */
@Service("customerService")
@Slf4j
public class CustomerServiceImpl implements CustomerService {



    @Autowired
    private AppointOrderService orderService;

    @Autowired
    private WechatUserMapper wechatUserMapper;
    /**
     * 卖家端分页
     * @param offset
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<WeChatUser> getWeChatUserPages(Integer offset, Integer pageSize) {
        PageHelper.startPage(offset,pageSize);
        List<WeChatUser>  weChatUsers = wechatUserMapper.selectAll();
        PageInfo<WeChatUser> pageInfo = new PageInfo<>(weChatUsers);
        return pageInfo;
    }




    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid,orderId);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if (orderDTO == null){
            log.error("[取消订单]查不到该订单,orderId={}",orderId);
            throw new AppointException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }



    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getCustomerOpenid().equalsIgnoreCase(openid)){
            log.error("[查询订单] 订单openid不一致，openid={},orderDto={}",openid,orderDTO);
            throw new AppointException(ResultEnum.ORDER_OWNER_ERROR);
        }
        // 添加订单详情

        return orderDTO;
    }




}