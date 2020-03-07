package com.mi.haircut.controller;

import com.mi.haircut.Vo.AppointmentVo;
import com.mi.haircut.Vo.ResultVo;
import com.mi.haircut.domain.AppointCategory;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.mapper.AppointCategoryMapper;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointCategoryService;
import com.mi.haircut.service.AppointMentService;
import com.mi.haircut.utils.ResultVoUtil;
import com.mi.haircut.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.POST;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Rong
 * @date : 2019/12/10
 * @Desc: 预约控制器s
 */


@RestController
@RequestMapping("/appoint")
@Slf4j
public class BuyerAppointController {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointMentService appointMentService;

    @Autowired
    private AppointCategoryMapper appointCategoryMapper;

    @Autowired
    private AppointCategoryService appointCategoryService;


    /**
     * 查询所有预约和类别
     */
    @GetMapping("api/appointsAndCategorys")
    public ResultVo AppointsAndCategorys(){
        //获取预约和分类
        List<AppointmentVo> appointmentVoList = appointCategoryService.getAppointAndCategory();
        return ResultVoUtil.success(appointmentVoList);
//        return ResultVoUtil.success();
    }



    @GetMapping("api/appoinstList")
    public ResultVo<Appointment> getAppointList(){

        List<Appointment> appointmentList = appointmentMapper.selectAll();
        //存储服务类型不重复
        Set<Appointment> appointments = new HashSet<>();
        Appointment appoint;
        for (Appointment appointment : appointmentList){
            appoint = new Appointment();
            appoint.setAppointName(appointment.getAppointName());
            appointments.add(appoint);
        };
        log.info("[查询预约列表] result = {}", appointments);
        return ResultVoUtil.success(appointments);
    }


    /**
     * 获取日期和服务
     * @param date
     * @param appointName
     * @return
     */
    @RequestMapping(value = "api/getAppointDateAndName",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultVo<Appointment> getAppointDateAndName(String date,String appointName){

//        Date dateWeek = TimeUtil.strParseDates(date);
        log.info("=======>"+date+"========"+appointName);
        if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(appointName)){

            List<Appointment> appointments = appointMentService.getAppointDateAndName(date,appointName);

            log.info("[查询日期和服务列表] result = {}",appointments);
            return ResultVoUtil.success(appointments);
        }
        return null;
    }


    @RequestMapping(value = "api/getAppointId",method = RequestMethod.GET,produces ="application/json;charset=UTF-8" )
    public ResultVo getAppointId(String appointId){
        log.info("【开始查询预约编号】 result = {}",appointId);
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointId);
        return ResultVoUtil.success(appointment);
    }




}