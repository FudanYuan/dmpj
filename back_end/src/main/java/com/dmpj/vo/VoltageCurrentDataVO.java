package com.dmpj.vo;

import lombok.Data;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 13:15
 */
@Data
public class VoltageCurrentDataVO {
    /**
     * 时间段字符串
     */
    private String time;

    /**
     * 起始电压
     */
    private Double start_voltage;

    /**
     * 终止电压
     */
    private Double end_voltage;

    /**
     * 起始电流
     */
    private Double start_current;

    /**
     * 终止电压
     */
    private Double end_current;

}
