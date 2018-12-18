package com.dmpj.vo;

import lombok.Data;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 13:13
 */
@Data
public class TemperatureDataVO {
    /**
     * 时间戳
     */
    private String time;

    /**
     * 最高温度
     */
    private Double max_temperature;

    /**
     * 最低温度
     */
    private Double min_temperature;
}
