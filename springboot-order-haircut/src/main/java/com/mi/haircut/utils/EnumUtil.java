package com.mi.haircut.utils;


import com.mi.haircut.enums.CodeEnum;

/**
 * 枚举工具类
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return  null;
    }
}