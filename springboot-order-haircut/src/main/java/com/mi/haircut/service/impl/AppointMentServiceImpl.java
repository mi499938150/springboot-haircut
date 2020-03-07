package com.mi.haircut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.enums.AppointStatusEnum;
import com.mi.haircut.enums.ResultEnum;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointMentService;
import com.mi.haircut.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:
 */
@Service
@Slf4j
public class AppointMentServiceImpl implements AppointMentService{

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Appointment> findByAll() {
        return appointmentMapper.selectAll();
    }

    @Override
    public PageInfo<Appointment> getAppointmentPages(Integer offset, Integer pageSize) {

        PageHelper.startPage(offset,pageSize);
        List<Appointment> appointments = appointmentMapper.selectAll();
        PageInfo<Appointment> pageInfo = new PageInfo<>(appointments);
        return pageInfo;
    }

    @Override
    public Appointment onSale(String appointId) {
        Appointment appointmentInfo = appointmentMapper.selectByPrimaryKey(appointId);
        if (appointId == null){
            throw new AppointException(ResultEnum.APPOINT_NOT_EXIST);
        }
        if (appointmentInfo.getAppointStatusEnum() == AppointStatusEnum.UP){
            throw new AppointException(ResultEnum.APPOINT_STATUS_ERROR);
        }
        //更新状态
        appointmentInfo.setAppointStatus(AppointStatusEnum.UP.getCode());
        appointmentMapper.updateByPrimaryKey(appointmentInfo);
        return appointmentInfo;
    }

    @Override
    public Appointment offSale(String appointId) {
        Appointment appointmentInfo = appointmentMapper.selectByPrimaryKey(appointId);
        if (appointId == null){
            throw new AppointException(ResultEnum.APPOINT_NOT_EXIST);
        }
        if (appointmentInfo.getAppointStatusEnum() == AppointStatusEnum.DOWN){
            throw new AppointException(ResultEnum.APPOINT_STATUS_ERROR);
        }
        //更新状态
        appointmentInfo.setAppointStatus(AppointStatusEnum.DOWN.getCode());
        appointmentMapper.updateByPrimaryKey(appointmentInfo);
        return appointmentInfo;
    }

    /**
     * 用户查询日期和服务
     * @param date
     * @param appointName
     * @return
     */
    @Override
    public List<Appointment> getAppointDateAndName(String date, String appointName) {

        return appointmentMapper.getAppointDateAndName(date,appointName);
    }
}