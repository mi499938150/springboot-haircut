package com.mi.haircut.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:  预约类别
 */
@Data
@Table(name = "appoint_category")
public class AppointCategory implements Serializable {


    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}