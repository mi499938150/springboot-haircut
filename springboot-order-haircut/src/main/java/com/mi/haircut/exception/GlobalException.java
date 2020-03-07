package com.mi.haircut.exception;

import lombok.Data;

/**
 * @author : Rong
 * @date : 2019/12/8
 * @Desc:
 */

@Data
public class GlobalException extends RuntimeException {


    private String code;

    private String msg;

    public GlobalException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}