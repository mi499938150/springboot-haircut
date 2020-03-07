package com.mi.haircut.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.mi.haircut.Vo.ResultVo;
import com.mi.haircut.config.AlipayConfig;
//import com.mi.haircut.service.WapPayService;
import com.mi.haircut.converter.OrderFormOrderDtoConverter;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.form.OrderForm;
import com.mi.haircut.service.AppointOrderService;
import com.mi.haircut.service.WapPayService;
import com.mi.haircut.utils.ResultVoUtil;
import com.mi.haircut.utils.keyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.soap.Addressing;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/1/26
 * @Desc:
 */
@RestController
@RequestMapping("alipay")
@Slf4j
public class WapPayController {

    @Autowired
    private  AppointOrderService orderService;

    @Autowired
    private WapPayService wapPayService;

//    支付成功返回主页面
    public static String redirectUrl = "www.gouptea.com/alipay/alipay-return_url";

    @RequestMapping("index")
    public ModelAndView aliPay(){
        return new ModelAndView("alipay/index");
    }

    @RequestMapping("wapPayServlet")
    public String doWapPay(@Valid OrderForm orderForm,
                                               BindingResult bindingResult,
                           @RequestParam(value = "subject")String subject,
                           @RequestParam(value = "total_amount")String total_amount){

        log.info("【开始创建订单....】");
        log.info("【获取参数】result = {}",orderForm);
        log.info("[---------]");
        if (bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确,orderForm={}",orderForm);
            throw new AppointException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO =  OrderFormOrderDtoConverter.convert(orderForm);
        OrderDTO createResult = orderService.create(orderDTO);

        //获得初始化的AlipayClient,将上面创建的配置类中的变量设置到该对象中
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数,并把配置类中的两个路径设置进去  PC端
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //如果想正常调用接口则需要传一些必要参数,out_trade_no:订单号,保证唯一性,支付宝根据该参数生成你的支付单号,total_amount:需要支付的金额,注意是String型,且金额计数的分割","不能存在(例如1,000就是错误的参数),subject:对物品进行描述,product_code:是支付类型.更多详细的参数信息参考链接: [https://docs.open.alipay.com/api_1/alipay.trade.page.pay](https://docs.open.alipay.com/api_1/alipay.trade.page.pay)
        String orderId = keyUtil.genUniqueKey();
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ createResult.getOrderId() +"\","
                    + "\"total_amount\":\""+total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"product_code\":\"QUICK_WAP_PAY\"}");
            // FAST_INSTANT_TRADE_PAY PC
            // QUICK_WAP_PAY mobile
            //请求
            String result;
            //发送请求并返回
            result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println("*********************\n返回结果为："+result);
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("alipay-callback-return-sult")
    public String successResult(HttpServletRequest request, HttpServletResponse response){
        //可以根据request.getParameterMap()获得到调用接口时传递的一些参数去做后续的业务处理
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] out_trade_nos = parameterMap.get("out_trade_no");
        String id = out_trade_nos[0];
        log.info("【return】 id = {},time = {}",id,new Date());
        //跳转到其他页面或重定向到其他方法
        //修改订单支付状态
        wapPayService.updateInfo(id);

        return "";

    }
    @RequestMapping("alipay-callback-notify-url")
    public ResultVo failResult(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] out_trade_nos = parameterMap.get("out_trade_no");
        String uuid = out_trade_nos[0];
        log.info("[异步通知]");
        log.info("【notify】 uuid = {},time = {}",uuid,new Date());
        return ResultVoUtil.success(uuid);
    }



}