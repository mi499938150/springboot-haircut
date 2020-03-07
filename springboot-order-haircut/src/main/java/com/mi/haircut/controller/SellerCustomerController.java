package com.mi.haircut.controller;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.domain.WeChatUser;
import com.mi.haircut.mapper.WechatUserMapper;
import com.mi.haircut.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/1/18
 * @Desc: 客户信息
 */
@RestController
@RequestMapping("/seller/customer/")
@Slf4j
public class SellerCustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("list")
    public ModelAndView list(
            @RequestParam(value = "page",defaultValue = "0")Integer offset,
            @RequestParam(value = "size", defaultValue = "10")Integer size,
            Map<String,Object> map){
        PageInfo<WeChatUser> pageInfo = customerService.getWeChatUserPages(offset,size);
        map.put("weChatUserInfoPage",pageInfo);
        return new ModelAndView("customer/list",map);
    }
}