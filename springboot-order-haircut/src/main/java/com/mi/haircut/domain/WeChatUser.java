package com.mi.haircut.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:  微信授权暂用实体
 */
@Data
@Table(name = "wechat_user")
public class WeChatUser implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String openId;
    private String nickName;
    private String sexDesc;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;

}