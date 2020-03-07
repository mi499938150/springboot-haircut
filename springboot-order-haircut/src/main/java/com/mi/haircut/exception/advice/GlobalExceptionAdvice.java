package com.mi.haircut.exception.advice;

import com.mi.haircut.exception.GlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2019/12/8
 * @Desc:
 */

//@RestControllerAdvice
public class GlobalExceptionAdvice {


    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */

//    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception e){
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", e.getMessage());
        return map;
    }


    /**
     * 拦截捕捉自定义异常 GlobalException.class
     * @param e
     * @return
     */
//    @ExceptionHandler(value = GlobalException.class)
    public ModelAndView selfErrorHandler(GlobalException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", e.getCode());
        modelAndView.addObject("msg", e.getMsg());
        return modelAndView;
    }

}