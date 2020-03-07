package com.mi.haircut.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:   客户信息
 */
@Data
@Table(name = "customer_info")
public class CustomerInfo {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private  String customerId;

    /**微信openid**/
    private BigDecimal openId;

    /**客户姓名**/
    private String customerName;

    /**客户密码暂无**/
    private String customerPwd;

    private Date createTime;

    private Date updateTime;
}