//package com.mi.haircut.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.mi.haircut.domain.Appointment;
//import com.mi.haircut.mapper.AppointmentMapper;
//import com.mi.haircut.utils.keyUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.List;
//
///**
// * @author : Rong
// * @date : 2019/12/9
// * @Desc:
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class AppointMentServiceImplTest {
//
//    @Autowired
//    private AppointMentServiceImpl appointMentService;
//
//    @Autowired
//    private AppointmentMapper appointmentMapper;
//
//    @Test
//    public void findByAll(){
//        List<Appointment> appointmentList = appointMentService.findByAll();
//        System.out.println("=============="+ appointmentList);
//    }
//
//
//    @Test
//    public void insertAppoint(){
//        Appointment appointment = new Appointment();
//        for (int i = 0 ; i<20;i++) {
//            appointment.setAppointId(keyUtil.genUniqueKey());
//            appointment.setAppointName("洗发");
//            appointment.setAppointPrice(new BigDecimal(20));
//            appointment.setAppointStatus(0);
//            appointment.setCategoryType(0);
//            int insert = appointmentMapper.insert(appointment);
//            if (insert > 0) {
//                log.info("插入成功===");
//            }
//        }
//    }
//
//    @Test
//    public void getPages(){
//        PageHelper.startPage(0,10);
//        List<Appointment> appointments = appointmentMapper.selectAll();
//        PageInfo<Appointment> pageInfo = new PageInfo<>(appointments);
//        System.out.println("======"+pageInfo.getSize());
//    }
//}