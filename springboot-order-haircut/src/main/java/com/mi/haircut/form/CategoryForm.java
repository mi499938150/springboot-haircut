package com.mi.haircut.form;

import lombok.Data;

import java.util.Date;

/**
 * Created by 廖师兄
 * 2017-07-23 21:43
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;


    private Date createTime;

    private Date updateTime;
}
