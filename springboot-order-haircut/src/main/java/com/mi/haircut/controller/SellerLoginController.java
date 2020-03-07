package com.mi.haircut.controller;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/1/30
 * @Desc:
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class SellerLoginController {

    @RequestMapping("/login")
    public ModelAndView ToAdmin(){

        return new ModelAndView("/admin/login");
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param map
     * @return
     */
    @RequestMapping("tologin")
    public ModelAndView ToLogin(@RequestParam("username") String username,
                                @RequestParam("password")String password, Map<String, Object> map){
        log.info("【验证用户密码】");
        if (username.equals("admin")&&password.equals("123456")){
            String admin  = "admin";
            log.info("【验证用户成功】");
            return new ModelAndView("/appoint/index");
        }
        //否则
        map.put("msg","用户名或密码错误");
        log.info("【验证用户失败】");
        return new ModelAndView("/admin/login/",map);
    }

    @RequestMapping("logout")
    public ModelAndView Logout(){
        return new ModelAndView("/admin/login");
    }


}