package com.mi.haircut.service;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.Vo.AppointmentVo;
import com.mi.haircut.domain.AppointCategory;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/15
 * @Desc: 预约分类接口
 */
public interface AppointCategoryService {

    /**
     * 获取所有分类
     * @param categoryTypeList
     * @return
     */
    List<AppointCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<AppointmentVo> getAppointAndCategory();

    /**
     * 分类分页
     * @param offset
     * @param pageSize
     * @return
     */
    PageInfo<AppointCategory> getAppointCategoryPages(Integer offset, Integer pageSize);
}
