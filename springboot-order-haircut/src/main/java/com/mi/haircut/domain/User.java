package com.mi.haircut.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2019/12/3
 * @Desc:   用户实体   暂无
 */
@Data
public class User implements Serializable {

    private Long Id;

    private String name;

    private String password;
}