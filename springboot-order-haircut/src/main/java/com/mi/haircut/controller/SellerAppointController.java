package com.mi.haircut.controller;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.AppointCategory;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.form.AppointmentForm;
import com.mi.haircut.mapper.AppointCategoryMapper;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointMentService;
import com.mi.haircut.utils.RedisUtil;
import com.mi.haircut.utils.TimeUtil;
import com.mi.haircut.utils.keyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc: 卖家
 */
@RestController
@RequestMapping("/seller/appoint/")
@Slf4j
public class SellerAppointController {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointMentService appointMentService;

    @Autowired
    private AppointCategoryMapper appointCategoryMapper;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "appointId",required = false) String appointId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(appointId)){
            Appointment appointInfo = appointmentMapper.selectByPrimaryKey(appointId);
//            map.put("appointWeek",TimeUtil.dataformat(appointInfo.getAppointWeek()));
            map.put("appointInfo",appointInfo);
        }
        //查询所有类目
        List<AppointCategory> categoryList = appointCategoryMapper.selectAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("appoint/index",map);
    }

    @GetMapping("list")
    public ModelAndView list(
            @RequestParam(value = "page",defaultValue = "0")Integer offset,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageInfo<Appointment> pageInfo = appointMentService.getAppointmentPages(offset,size);
        map.put("appointInfoPage",pageInfo);
        return new ModelAndView("appoint/list",map);
    }


    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid AppointmentForm form, BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/haircut/seller/appoint/index");
            return new ModelAndView("common/error",map);
        }
        Appointment appointmentInfo = new Appointment();
        try{
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getAppointId())){
                appointmentInfo = appointmentMapper.selectByPrimaryKey(form.getAppointId());
                BeanUtils.copyProperties(form,appointmentInfo);

                appointmentInfo.setAppointWeek(TimeUtil.queryDateByflag2((TimeUtil.strParseDates(form.getAppointWeek())),1) );
                appointmentInfo.setAppointStatus(0);
                appointmentInfo.setUpdateTime(new Date());
                log.info("[categoryType ===],typeFrom = {}",form.getCategoryType());
                appointmentMapper.updateByPrimaryKey(appointmentInfo);
                log.info("[预约修改数据 ----->>>删除缓存...]");
//                if (!CollectionUtils.isEmpty((List<AppointCategory>) redisUtil.get("appointmentList"))){
//                    log.info("[预约缓存存在----->>>开始删除缓存]");
//                    redisUtil.del("appointmentList");
//                }
            }else {
                form.setAppointId(keyUtil.genUniqueKey());
                BeanUtils.copyProperties(form,appointmentInfo);
                appointmentInfo.setAppointStatus(0);
                appointmentInfo.setAppointWeek(TimeUtil.strParseDates(form.getAppointWeek()));
                appointmentInfo.setCreateTime(new Date());
                appointmentMapper.insert(appointmentInfo);
//                log.info("[预约添加数据 ----->>>删除缓存...]");
//                if (!CollectionUtils.isEmpty((List<AppointCategory>) redisUtil.get("appointmentList"))){
//                    log.info("[预约缓存存在----->>>开始删除缓存]");
//                    redisUtil.del("appointmentList");
//                }
            }
        }catch (AppointException e){
            map.put("msg",e.getMessage());
            map.put("url", "/seller/appoint/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url","/haircut/seller/appoint/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 预约时间上架
     * @param appointId
     * @param map
     * @return
     */
    @RequestMapping("on_sale")
    public ModelAndView onSale(@RequestParam("appointId")String appointId,Map<String,Object> map){

        try {
            appointMentService.onSale(appointId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/haircut/seller/appoint/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.APPOINT_STATUS_UP.getMessage());
        map.put("url","/haircut/seller/appoint/list");
        return new ModelAndView("common/success",map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("appointId")String appointId,Map<String,Object> map){

        try {
            appointMentService.offSale(appointId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/haircut/seller/appoint/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.APPOINT_STATUS_DOWN.getMessage());
        map.put("url","/haircut/seller/appoint/list");
        return new ModelAndView("common/success",map);
    }


}