package com.mi.haircut.controller;

import com.mi.haircut.Vo.ResultVo;
import com.mi.haircut.domain.WeChatUser;
import com.mi.haircut.mapper.WechatUserMapper;
import com.mi.haircut.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Rong
 * @date : 2020/1/14
 * @Desc: 通过Openid获取用户信息
 */

@RestController
@RequestMapping("order/userInfo")
@Slf4j
public class WeChatInfoController {

    @Autowired
    private WechatUserMapper wechatUserMapper;

    @RequestMapping(value = "api/getByOrderyOpenId",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultVo getByOrderOpenId(@RequestParam("openid") String openid){

        WeChatUser weChatUser = wechatUserMapper.selectByPrimaryKey(openid);
        return ResultVoUtil.success(weChatUser);
    }
}