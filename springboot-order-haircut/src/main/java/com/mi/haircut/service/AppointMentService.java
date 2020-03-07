package com.mi.haircut.service;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.Appointment;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/9
 * @Desc:
 */
public interface AppointMentService {


    /**
     * 查询所有
     * @return
     */
    List<Appointment> findByAll();
    /**
     * 查询分页
     * @param offset
     * @param pageSize
     * @return
     */
    PageInfo<Appointment> getAppointmentPages(Integer offset, Integer pageSize);

    /**
     * 上架
     * @param appointId
     * @return
     */
    Appointment onSale(String appointId);


    /**
     * 下架
     * @param appointId
     * @return
     */
    Appointment offSale(String appointId);


    List<Appointment> getAppointDateAndName(String date,String appointName);
}