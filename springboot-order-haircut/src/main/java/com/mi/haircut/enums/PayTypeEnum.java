package com.mi.haircut.enums;

import lombok.Getter;

/**
 * 支付状态
 */
@Getter
public enum PayTypeEnum implements CodeEnum {

    WXIN(0,"微信支付"),
    APAY(1,"支付宝支付");
    private Integer code;

    private String message;

    PayTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}