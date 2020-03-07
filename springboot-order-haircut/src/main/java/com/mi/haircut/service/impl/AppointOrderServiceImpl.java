package com.mi.haircut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.haircut.Vo.OrderDetailInfoVo;
import com.mi.haircut.Vo.OrderDetailVo;
import com.mi.haircut.converter.OrderDtoConverter;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.domain.Order;
import com.mi.haircut.domain.OrderDetail;
import com.mi.haircut.dto.OrderDTO;
import com.mi.haircut.enums.OrderStatusEnum;
import com.mi.haircut.enums.PayStatusEnum;
import com.mi.haircut.enums.PayTypeEnum;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.AppointOrderMapper;
import com.mi.haircut.mapper.AppointOrdrDetailMapper;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointMentService;
import com.mi.haircut.service.AppointOrderService;
import com.mi.haircut.service.PayService;
import com.mi.haircut.utils.RedisUtil;
import com.mi.haircut.utils.keyUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Rong
 * @date : 2019/12/18
 * @Desc:
 */
@Service
@Slf4j
public class AppointOrderServiceImpl implements AppointOrderService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointOrderMapper appointOrderMapper;

    @Autowired
    private AppointOrdrDetailMapper appointOrdrDetailMapper;

    @Autowired
    private PayService payService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId  = keyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //第一种 List<>
        // 1. 查询 预约(价格)
        if (orderDTO.getAppointId() != null){
            Appointment appointmentInfo = appointmentMapper.selectByPrimaryKey(orderDTO.getAppointId());
            if (appointmentInfo == null){
                throw new AppointException(ResultEnum.APPOINT_NOT_EXIST);
            }
            // 2. 计算订单总价
            orderAmount = appointmentInfo.getAppointPrice()
                    .multiply(new BigDecimal(orderDTO.getAppointQuantity()))
                    .add(orderAmount);
            // 3. 订单详情入库 orderdetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(keyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setAppointQuantity(orderDTO.getAppointQuantity());
            BeanUtils.copyProperties(appointmentInfo,orderDetail);
            appointOrdrDetailMapper.insert(orderDetail);
        }
        // 3. 写入订单数据库 order
        Order order = new Order();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,order);
        order.setOrderAmount(orderAmount);
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());
        order.setPaymentType(PayTypeEnum.APAY.getCode());
        appointOrderMapper.insert(order);
         return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Order order = appointOrderMapper.selectByPrimaryKey(orderId);
        //判断订单是否存在
        if (order == null){
            throw new AppointException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断订单详情是否存在
        List<OrderDetail> orderDetails = appointOrdrDetailMapper.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw  new AppointException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        // 查询 订单
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid, Integer offset, Integer pageSize) {
        Order order = new Order();
        order.setCustomerOpenid(buyerOpenid);
        order.getCustomerOpenid();
        PageHelper.offsetPage(offset,pageSize);
        List<Order> orders = appointOrderMapper.select(order);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        List<OrderDTO> orderDTOS = OrderDtoConverter.convert(orderPageInfo.getList());
        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orderDTOS);
        return orderDTOPageInfo;
    }

    @Override
    public OrderDTO findList(String customerOpenid) {
        Order order = new Order();
        order.setCustomerOpenid(customerOpenid);
        order.getCustomerOpenid();
        Order order1 = appointOrderMapper.selectOne(order);
        if (order1 == null){
            throw new AppointException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = appointOrdrDetailMapper.findByOrderId(order1.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new AppointException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO  = new OrderDTO();
        BeanUtils.copyProperties(order1,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


    //用户查询订单
    @Override
    public List<OrderDetailInfoVo> findByoOrderDetailList(String customerOpneid) {

        // 1. 查询用户Openid
        Order order = new Order();
        order.setCustomerOpenid(customerOpneid);
        order.getCustomerOpenid();

        // 判断缓存
//        List<Order> orders = redisUtil.get("")
//        List<Order> orders  = (List<Order>) redisUtil.get("orderDetailList");
//        if (CollectionUtils.isEmpty(orders)){
//        }
        List<Order> orders = appointOrderMapper.select(order);


        if (CollectionUtils.isEmpty(orders)){
            return null;
        }
        // 2. 查询所有订单Id
        ////Java lamba
        List<String> orderIdListIn = orders.stream()
                .map(e ->e.getOrderId())
                .collect(Collectors.toList());
        // 3. 查询所有详情

        List<OrderDetail> detailList = appointOrdrDetailMapper.findByOrderIdIn(orderIdListIn);
        // 4. 数据品装
        List<OrderDetailInfoVo> orderDetailVoList = new ArrayList<>();

        for (OrderDetail orderDetail : detailList){
            // 5. 添加详情
            OrderDetailInfoVo  detailInfoVo = new OrderDetailInfoVo();
            detailInfoVo.setAppointName(orderDetail.getAppointName());
            detailInfoVo.setAppointQuantity(orderDetail.getAppointQuantity());
            detailInfoVo.setAppointTime(orderDetail.getAppointTime());
            detailInfoVo.setAppointWeek(orderDetail.getAppointWeek());
            detailInfoVo.setAppointPrice(orderDetail.getAppointPrice());
            // 6. 添加订单
            List<OrderDetailVo> orderDetailVos = new ArrayList<>();
            for (Order order1 :orders){
                if (order1.getOrderId().equals(orderDetail.getOrderId())){
                    OrderDetailVo orderDetailVo  = new OrderDetailVo();
                    BeanUtils.copyProperties(order1,orderDetailVo);
                    orderDetailVos.add(orderDetailVo);
                }
            }
            detailInfoVo.setOrderDetailVoList(orderDetailVos);
            orderDetailVoList.add(detailInfoVo);
        }
        return orderDetailVoList;

    }




    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        Order order = new Order();
        // 判断订单状态
        if (! orderDTO.getOrderStatus()
                .equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]订单状态不正确,orderId={}, orderStatus={}",
                  orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new AppointException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,order);
        int orderUpdate = appointOrderMapper.updateByPrimaryKey(order);
        if (orderUpdate < 0 ){
            log.error("[取消订单]更新失败，order={}",order);
            throw new AppointException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //查询订单详细
        List<OrderDetail> orderDetails = appointOrdrDetailMapper
                .findByOrderId(orderDTO.getOrderId());
        orderDTO.setOrderDetailList(orderDetails);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单]中无商品详情,orderDTO={}",orderDTO);
            throw new AppointException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        // TODO tosomthing

        // 如果已经支付,需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            log.info("[订单号支付,已经开始退款...]");
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单]订单状态不正确,orderId={}, orderStauts = {}",
            orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new AppointException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        int orderUpdate = appointOrderMapper.updateByPrimaryKey(order);
        if (orderUpdate < 0 ){
            log.error("[完结订单]更新失败,order = {}",order);
            throw new AppointException(ResultEnum.ORDER_UPDATE_FAIL) ;
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付成功]订单状态不正确,orderId = {},orderStat={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new AppointException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付成功]顶顶那状态不正确,orderDTO={}",orderDTO);
            throw new AppointException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDTO.setPaymentType(1);
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        int orderUpdate = appointOrderMapper.updateByPrimaryKey(order);
        if (orderUpdate < 0 ){
            log.error("[订单支付成功]更新失败,order={}",order);
            throw new AppointException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     *
     * 查询卖家端订单分页
     * @param offset
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<OrderDTO> findList(Integer offset, Integer pageSize) {
        PageHelper.startPage(offset,pageSize);
        List<Order> orderList = appointOrderMapper.selectAll();
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        List<OrderDTO> orderDTOS = OrderDtoConverter.convert(orderPageInfo.getList());

        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orderDTOS);
        orderDTOPageInfo.setTotal(orderPageInfo.getTotal());
        orderDTOPageInfo.setPages(orderPageInfo.getPages());
        orderDTOPageInfo.setPageNum(orderPageInfo.getPageNum());
        orderDTOPageInfo.setPageSize(orderPageInfo.getPageSize());
        log.info("[[orderService] 总页数={},页面={}\",orderDTOPageInfo.getTotal(),orderDTOPageInfo.getPages()]");
        return orderDTOPageInfo;
    }
}