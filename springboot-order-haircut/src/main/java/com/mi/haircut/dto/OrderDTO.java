package com.mi.haircut.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mi.haircut.domain.OrderDetail;
import com.mi.haircut.enums.OrderStatusEnum;
import com.mi.haircut.enums.PayStatusEnum;
import com.mi.haircut.enums.PayTypeEnum;
import com.mi.haircut.utils.EnumUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/18
 * @Desc: 订单DTO实体类
 */
@Data
public class OrderDTO implements Serializable {

    /**订单id**/
    private String orderId;


    /**预约id**/
    private String appointId;

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

    /**订单数量**/
    private Integer appointQuantity;

    /**订单状态**/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**支付状态**/
    private Integer payStatus;

    /**订单支付方式    默认0  支付宝    1  微信**/
    private Integer paymentType;

    private String customerRemark;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail>  orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return  EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

    @JsonIgnore
    public PayTypeEnum getPayTypeEnum(){
        return EnumUtil.getByCode(paymentType,PayTypeEnum.class);
    }

}