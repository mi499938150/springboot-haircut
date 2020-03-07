package com.mi.haircut.domain;

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
 * @Desc:  订单详情实体
 */
@Data
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String detailId;

    /**订单id**/
    private String orderId;

    /**预约id**/
    private String appointId;

    /**预约名字**/
    private String appointName;

    /**预约价格**/
    private BigDecimal appointPrice;

    /**预约时间**/
    private String appointTime;
    /**预约数量**/
    private Integer appointQuantity;

    /**预约星期**/
    private Date appointWeek;

    /**留言**/
    private String remark;

    private Date createTime;

    private Date updateTime;
}