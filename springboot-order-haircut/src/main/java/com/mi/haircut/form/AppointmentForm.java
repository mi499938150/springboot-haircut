package com.mi.haircut.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc:
 */
@Data
public class AppointmentForm {

    private String appointId;

    /**名字*/
    private String appointName;

    /**日期*/
    private String appointWeek;

    /**时间 08:00*/
    private String appointTime;

    /**单价*/
    private BigDecimal appointPrice;

    /** 状态，0正常 1下架*/
    private Integer appointStatus;

    /**类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}