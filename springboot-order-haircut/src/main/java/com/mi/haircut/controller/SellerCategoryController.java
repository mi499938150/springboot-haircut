package com.mi.haircut.controller;

import com.github.pagehelper.PageInfo;
import com.mi.haircut.domain.AppointCategory;
import com.mi.haircut.exception.AppointException;
import com.mi.haircut.form.CategoryForm;
import com.mi.haircut.mapper.AppointCategoryMapper;
import com.mi.haircut.service.AppointCategoryService;
import com.mi.haircut.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2019/12/18
 * @Desc: 分类控制器
 */
@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {


    @Autowired
    private AppointCategoryMapper appointCategoryMapper;

    @Autowired
    private AppointCategoryService appointCategoryService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "0")Integer offset,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String,Object> map) {
        PageInfo<AppointCategory> categoryPages = appointCategoryService.getAppointCategoryPages(offset,size);

        map.put("categoryInfoPage",categoryPages);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            AppointCategory appointCategory = appointCategoryMapper.selectByPrimaryKey(categoryId);
            map.put("category", appointCategory);
        }

        return new ModelAndView("category/index", map);
    }



    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/haircut/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        AppointCategory appointCategory = new AppointCategory();
        try {
            if (form.getCategoryId() != null) {
                appointCategory = appointCategoryMapper.selectByPrimaryKey(form.getCategoryId());
                BeanUtils.copyProperties(form, appointCategory);
                appointCategory.setUpdateTime(new Date());
                appointCategoryMapper.updateByPrimaryKey(appointCategory);


            }else {
                BeanUtils.copyProperties(form,appointCategory);
                appointCategory.setCreateTime(new Date());
                appointCategoryMapper.insert(appointCategory);
            }

        } catch (AppointException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/haircut/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}