//package com.mi.haircut.controller;
//
//
//import com.lly835.bestpay.model.PayResponse;
//import com.mi.haircut.dto.OrderDTO;
//import com.mi.haircut.enums.ResultEnum;
//import com.mi.haircut.exception.AppointException;
//import com.mi.haircut.service.AppointOrderService;
//import com.mi.haircut.service.PayService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//
///**
// * 支付
// */
//@RestController
//@RequestMapping("/pay")
//public class PayController {
//
//    @Autowired
//    private AppointOrderService orderService;
//
//    @Autowired
//    private PayService payService;
//
//    @GetMapping("/create")
//    public ModelAndView create(@RequestParam("orderId")String orderId,
//                               @RequestParam("returnUrl")String returnUrl,
//                               Map<String,Object> map){
//
//        //1.查询订单
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO == null){
//            throw new AppointException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        //2.发起支付
//        PayResponse payResponse = payService.create(orderDTO);
//        map.put("payResponse",payResponse);
//        map.put("returnUrl",returnUrl);
//
//
//        return new ModelAndView("pay/create",map);
//
//    }
//
//    /**
//     * 微信异步通知
//     * 需要配置
//     */
//    @PostMapping("/notify")
//    public ModelAndView notify(@RequestBody String notifyData){
//
//
//        payService.notify(notifyData);
//       //返回给微信处理
//        return new ModelAndView("pay/success");
//    }
//
//
//
//
//}