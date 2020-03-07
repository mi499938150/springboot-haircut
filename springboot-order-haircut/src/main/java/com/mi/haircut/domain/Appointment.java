package com.mi.haircut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mi.haircut.enums.AppointStatusEnum;
import com.mi.haircut.utils.EnumUtil;
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
 * @Desc: 预约实体类
 */
@Data
@Table(name = "appoint_ment")
public class Appointment implements Serializable {


    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String appointId;

    /**名字*/
    private String appointName;

    /**日期*/
    private Date appointWeek;

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

    @JsonIgnore
    public AppointStatusEnum getAppointStatusEnum(){
        return EnumUtil.getByCode(appointStatus,AppointStatusEnum.class);
    }
}