package com.mi.haircut.service;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.WeChatUser;
import com.mi.haircut.dto.OrderDTO;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/1/4
 * @Desc:   用户
 */
public interface CustomerService {

    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);


    /**
     * 卖家端
     */
    PageInfo<WeChatUser> getWeChatUserPages(Integer offset, Integer pageSize);

}
