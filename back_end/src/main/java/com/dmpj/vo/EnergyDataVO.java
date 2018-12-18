package com.dmpj.vo;

import lombok.Data;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 13:10
 */
@Data
public class EnergyDataVO {
    /**
     * 时间戳
     */
    private String time;

    /**
     * 开始SOC
     */
    private Double start_soc;

    /**
     * 结束SOC
     */
    private Double end_soc;

    /**
     * 预测能量
     */
    private Double predict_energy;

    /**
     * 真实能量
     */
    private Double actual_energy;
}
