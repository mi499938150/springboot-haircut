package com.mi.haircut.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/1/4
 * @Desc:
 */
@Data
public class OrderDetailVo {

    private String orderId;

    private String customerOpenid;

    /**客户姓名**/
    private String customerName;

    /**客户电话**/
    private String customerPhone;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Integer paymentType;

    private Date createTime;


}