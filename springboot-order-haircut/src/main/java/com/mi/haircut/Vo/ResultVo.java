package com.mi.haircut.Vo;

import lombok.Data;

/**
 * @author : Rong
 * @date : 2019/12/3
 * @Desc:  封装数据类
 */

@Data
public class ResultVo<T> {

    /**错误码*/
    private Integer code;

    /**提示信息*/
    private String msg ;

    /**具体内容*/
    private T data;
}