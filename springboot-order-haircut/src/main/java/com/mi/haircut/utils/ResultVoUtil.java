package com.mi.haircut.utils;

import com.mi.haircut.Vo.ResultVo;

/**
 * @author : Rong
 * @date : 2019/12/3
 * @Desc: 封装参数显示
 */

public class ResultVoUtil {


    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(){

        return success(null);
    }

    public static ResultVo error(Integer code, String msg){
        ResultVo resultVo = new ResultVo();

        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    //判断订单详情的时候
    public static ResultVo success(Object object,Integer code){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(code);
        resultVo.setMsg("成功");
        return resultVo;
    }
}