package com.mi.haircut.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 格式化
 */
public class JsonUtil {

    public static String toJson (Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
