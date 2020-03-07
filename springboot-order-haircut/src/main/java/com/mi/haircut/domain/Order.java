package com.mi.haircut.domain;

import com.mi.haircut.enums.OrderStatusEnum;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:   订单实体
 */
@Data
@Table(name = "appoint_order")
public class Order implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String orderId;

    /**客户姓名**/
    private String customerName;

    /**客户电话**/
    private String customerPhone;

    /**客户地址 暂无**/
    private String customerAddress;

    /**微信openid**/
    private String customerOpenid;

    /**订单总金额**/
    private BigDecimal orderAmount;

    /**订单状态**/
    // 默认0  1订单完成   2取消订单/退票
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**支付状态**/
    // 0 未支付  1支付     3已退
    private Integer payStatus;

    /**订单支付方式    默认0  支付宝    1  微信**/
    private Integer paymentType;

    private String customerRemark;

    private Date createTime;

    private Date updateTime;
}