package com.mi.haircut.form;

/**
 * 表单验证
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {


    /**
     * 预约编号
     */
    private String appointId;

    /**
     * 买家姓名
     */
//    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
//    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
//    @NotEmpty(message = "地址必填")
//    private String address;


    /**
     *  买家微信openid
     */

//    @NotEmpty(message = "openid必填")
    private String openid;


    /**
     * 数量
     */
    private  Integer appointQuantity;



}