package com.mi.haircut.controller;

import com.mi.haircut.Vo.ResultVo;
import com.mi.haircut.domain.WeChatUser;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.WechatUserMapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author : Rong
 * @date : 2020/1/12
 * @Desc: 微信授权
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatUserMapper wechatUserMapper;
    /**
     * 微信授权
     */
    @GetMapping("authorize")
    public String  authorize(@RequestParam("returnUrl")String returnUrl, HttpServletResponse response) throws IOException {
        // 1. 配置
        // 2. 调用方法
        String url = "http://www.gouptea.top/haircut/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("[微信网页授权]获取code,result={}",redirectUrl.toString());
        return "redirect:" + redirectUrl;
      }


@GetMapping("/userInfo")
public ModelAndView userInfo(@RequestParam("code") String code, @RequestParam("state")String returnUrl, HttpServletResponse response) throws IOException {
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
    try{
        wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
        log.error("[微信网页授权]{}",e);
        throw new AppointException(ResultEnum.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
    }
    String openId = wxMpOAuth2AccessToken.getOpenId();
    log.info("[微信openid]openid={}",openId);

    String str = returnUrl + "?openid=" + openId;
    log.info("【微信网页返回地址】:{}",str);
    try {
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
        log.info("【微信网页授权获】获取用户信息：{}",wxMpUser);
        //判断是否为空
        if (!StringUtils.isEmpty(wxMpUser)){
//            //查询是否存在
            WeChatUser wChatUser = wechatUserMapper.selectByPrimaryKey(openId);
            //不存在就添加到数据库中
            if (StringUtils.isEmpty(wChatUser)) {
                WeChatUser weChatUser = new WeChatUser();
                weChatUser.setOpenId(wxMpUser.getOpenId());
                weChatUser.setSex(wxMpUser.getSex());
                weChatUser.setSexDesc(wxMpUser.getSexDesc());
                weChatUser.setNickName(wxMpUser.getNickname());
                weChatUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
                weChatUser.setLanguage(wxMpUser.getLanguage());
                weChatUser.setCountry(wxMpUser.getCountry());
                weChatUser.setProvince(wxMpUser.getProvince());
                weChatUser.setCity(wxMpUser.getCity());
                wechatUserMapper.insert(weChatUser);
            }
        }
    } catch (WxErrorException e) {
        e.printStackTrace();
    }
    return new ModelAndView(new RedirectView(str));
   }

}