package com.mi.haircut.enums;

import lombok.Getter;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc:
 */
@Getter
public enum  ResultEnum {

    SUCCESS(0,"取消成功"),
    PARAM_ERROR(1,"参数不正确"),
    APPOINT_NOT_EXIST(10,"预约时间不存在"),
//    PRODUCT_STOCK_ERROR(11,"商品库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WX_MP_ERROR(20,"微信公众账号方面错误"),
    WXPAY_NOTIFY_MONEY_ERROR(21,"微信异步通知校验金额不通过"),
    ORDER_CANCEL_SUCCESS(22,"订单取消成功"),
    ORDER_FINISH_SUCCESS(23,"订单完结成功"),
    APPOINT_STATUS_ERROR(24,"预约时间已上架"),
    APPOINT_STATUS_UP(25,"预约时间上架成功"),
    APPOINT_STATUS_DOWN(26,"预约下架成功");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}