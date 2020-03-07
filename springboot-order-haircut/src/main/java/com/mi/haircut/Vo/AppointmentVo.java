package com.mi.haircut.Vo;

import lombok.Data;

import java.util.List;

/**
 * @author : Rong
 * @date : 2019/12/12
 * @Desc: 返回前端预约详情数据
 */
@Data
public class AppointmentVo {

    /**分类名称**/
    private String categoryName;

    /**分类类型**/
    private Integer categoryType;

    /**对应多个预约**/
    private List<AppointmentInfoVo> appointmentInfoVoList;

}