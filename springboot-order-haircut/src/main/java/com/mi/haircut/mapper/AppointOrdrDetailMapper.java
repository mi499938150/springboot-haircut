package com.mi.haircut.mapper;

import com.mi.haircut.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/19
 * @Desc:
 */
public interface AppointOrdrDetailMapper  extends Mapper<OrderDetail> {

    /**
     * 查询订单id
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

    /**
     * 查询多个订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderIdIn(@Param("idList") List<String> orderId);
}