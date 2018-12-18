package com.dmpj.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 13:17
 */
@Data
public class DataVO {
    /**
     * 充电能量列表
     */
    private List<EnergyDataVO> energyData;

    /**
     * 温度数据列表
     */
    private List<TemperatureDataVO> temperatureData;

    /**
     * 里程数据列表
     */
    private List<MileageDataVO> mileageData;

    /**
     * 电压-电流数据列表
     */
    private List<VoltageCurrentDataVO> voltageCurrentData;
}
