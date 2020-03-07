package com.mi.haircut.exception;

import com.mi.haircut.enums.ResultEnum;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc: 抛出异常
 */

public class AppointException extends RuntimeException {


    private Integer code;

    public AppointException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public AppointException(Integer code,String message){
        super(message);
        this.code = code;
    }
}