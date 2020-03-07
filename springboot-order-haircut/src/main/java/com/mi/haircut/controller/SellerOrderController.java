package com.mi.haircut.controller;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.AppointOrderMapper;
import com.mi.haircut.service.AppointOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author : Rong
 * @date : 2019/12/18
 * @Desc:
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {


    @Autowired
    private AppointOrderMapper appointOrderMapper;

    @Autowired
    private AppointOrderService appointOrderService;

    /**
     * 订单列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageInfo<OrderDTO> orderDTOPageInfo = appointOrderService.findList(offset, size);
        map.put("orderDTOPage", orderDTOPageInfo);
        return new ModelAndView("order/list", map);
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = appointOrderService.findOne(orderId);
        } catch (AppointException e) {
            log.error("[卖家端取消订单]查询订单异常");
            map.put("msg", e.getMessage());
            map.put("ur;", "/haircut/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    /**
     * 完结订单
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = appointOrderService.findOne(orderId);
            appointOrderService.finish(orderDTO);
        } catch (AppointException e) {
            log.error("[卖家端取消订单]查询订单异常");
            map.put("msg", e.getMessage());
            map.put("url", "/haircut/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/haircut/seller/order/list");
        return new ModelAndView("common/success");
    }

    /**
     * 取消订单
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){

        try {
            OrderDTO orderDTO = appointOrderService.findOne(orderId);
            appointOrderService.cancel(orderDTO);
        }catch (AppointException e){
            log.error("[卖家端取消订单]查询订单异常");
            map.put("msg",e.getMessage() );
            map.put("url","/haircut/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/haircut/seller/order/list");
        return new ModelAndView("common/success");

    }

}



