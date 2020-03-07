package com.mi.haircut.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Rong
 * @date : 2020/2/27
 * @Desc:  唤起支付宝页面
 */
@RestController
public class AliPayController {

    @GetMapping("/pay")
    public ModelAndView Pay(){
        return new ModelAndView("alipay/pay");
    }
}