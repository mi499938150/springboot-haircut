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
public class OrderDetailInfoVo {

    private String appointName;

    private Date appointWeek;

    private String appointTime;

    private Integer appointQuantity;

    private BigDecimal appointPrice;

    private List<OrderDetailVo> orderDetailVoList;
}