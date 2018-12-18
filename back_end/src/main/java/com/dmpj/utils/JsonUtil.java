package com.dmpj.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json 工具类
 *
 * @Author: Jeremy
 * @Date: 2018/8/29 22:30
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
