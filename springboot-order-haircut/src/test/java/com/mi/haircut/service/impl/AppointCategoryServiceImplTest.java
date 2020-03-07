//package com.mi.haircut.service.impl;
//
//import com.mi.haircut.domain.AppointCategory;
//import com.mi.haircut.domain.Appointment;
//import com.mi.haircut.mapper.AppointCategoryMapper;
//import com.mi.haircut.mapper.AppointmentMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author : Rong
// * @date : 2019/12/15
// * @Desc:
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public  class AppointCategoryServiceImplTest {
//
//
//    @Autowired
//    private AppointCategoryMapper appointCategoryMapper;
//
//    @Autowired
//    private AppointmentMapper appointmentMapper;
////
//    @Test
//    public void testCategoty(){
////         1. 查询所有预约时间
//        List<Appointment> appointments = appointmentMapper.selectAll();
//
////        // 2.查询类目(一次性查询)
////        //Java lamba
//        List<Integer> categoryTypeListIn = appointments.stream()
//                .map(e -> e.getCategoryType())
//                .collect(Collectors.toList());
//        System.out.println("======"+categoryTypeListIn);
//                List<AppointCategory> categoryTypeIn = appointCategoryMapper.findByCategoryTypeIn(categoryTypeListIn);
//        System.out.println("========"+categoryTypeIn);
//    }
//}