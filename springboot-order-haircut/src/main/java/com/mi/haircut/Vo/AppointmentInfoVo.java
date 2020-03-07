package com.mi.haircut.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2019/12/12
 * @Desc:  预约详情
 */
@Data
public class AppointmentInfoVo {



    private String appointName;

    private Date appointWeek;

    private String appointTime;

    private BigDecimal appointPrice;


}
