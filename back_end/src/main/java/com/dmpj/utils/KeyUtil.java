package com.dmpj.utils;

import java.util.Random;

/**
 * 主键工具类
 *
 * @Author: Jeremy
 * @Date: 2018/8/29 22:28
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
