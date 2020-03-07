package com.mi.haircut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.haircut.Vo.AppointmentInfoVo;
import com.mi.haircut.Vo.AppointmentVo;
import com.mi.haircut.domain.AppointCategory;
import com.mi.haircut.domain.Appointment;
import com.mi.haircut.mapper.AppointCategoryMapper;
import com.mi.haircut.mapper.AppointmentMapper;
import com.mi.haircut.service.AppointCategoryService;
import com.mi.haircut.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc:
 */

@Service
@Slf4j
public class AppointCategoryServiceImpl implements AppointCategoryService {

    @Autowired
    private AppointCategoryMapper appointCategoryMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<AppointCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return appointCategoryMapper.findByCategoryTypeIn(categoryTypeList);
    }


    @Override
    public List<AppointmentVo> getAppointAndCategory(){

        // 1. 查询所有预约时间
        List<Appointment> appointments = appointmentMapper.selectAll();
        // 2.查询类目(一次性查询)
        //Java lamba
        List<Integer> categoryTypeListIn = appointments.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        // 2. 查询类目
        List<AppointCategory> appointCategoryList =
                appointCategoryMapper.findByCategoryTypeIn(categoryTypeListIn);
        // 3. 数据拼装
        List<AppointmentVo> appointmentVoList = new ArrayList<>();
        for (AppointCategory appointCategory : appointCategoryList){
            AppointmentVo appointmentVo = new AppointmentVo();
            // 4. 添加分欸数据
            appointmentVo.setCategoryName(appointCategory.getCategoryName());
            appointmentVo.setCategoryType(appointCategory.getCategoryType());
            // 5. 添加预约数据
            List<AppointmentInfoVo> appointmentInfoVoList = new ArrayList<>();
            for (Appointment appointment : appointments){
                if (appointment.getCategoryType()
                        .equals(appointCategory.getCategoryType())){
                    AppointmentInfoVo appointmenInfo = new AppointmentInfoVo();
                    BeanUtils.copyProperties(appointment,appointmenInfo);
                    appointmentInfoVoList.add(appointmenInfo);
                }
            }
            appointmentVo.setAppointmentInfoVoList(appointmentInfoVoList);
            appointmentVoList.add(appointmentVo);
        }
        return appointmentVoList;
    }

    @Override
    public PageInfo<AppointCategory> getAppointCategoryPages(Integer offset, Integer pageSize) {
        PageHelper.startPage(offset,pageSize);

        List<AppointCategory> categories = categories = appointCategoryMapper.selectAll();;
        PageInfo<AppointCategory> pageInfo = new PageInfo<>(categories);
        return pageInfo;
    }


}