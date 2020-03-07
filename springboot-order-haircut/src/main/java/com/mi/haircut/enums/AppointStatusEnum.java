package com.mi.haircut.enums;

import lombok.Getter;

/**
 * @author : Rong
 * @date : 2019/12/17
 * @Desc:
 */
@Getter
public enum AppointStatusEnum implements CodeEnum{

    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    AppointStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}