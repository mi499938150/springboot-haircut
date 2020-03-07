package com.mi.haircut.utils;

import java.util.Random;

/**
 * 生成随机数
 */

public class keyUtil {

    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     * @return
     */
    public static  synchronized  String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}