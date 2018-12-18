package com.dmpj.utils;

import com.dmpj.enums.CodeEnum;

/**
 * 枚举工具类
 *
 * @Author: Jeremy
 * @Date: 2018/8/29 16:42
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
