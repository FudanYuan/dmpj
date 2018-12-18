package com.dmpj.service;

import com.dmpj.entity.TrainTestData;

import java.util.List;

/**
 * @Author: Jeremy
 * @Integer: 2018/12/13 20:37
 */
public interface TrainTestDataService {
    /**
     * 通过车辆id获取数据
     * @param vehicleId
     * @return
     */
    List<TrainTestData> findAllByVehicleId(Integer vehicleId);

    /**
     * 获取某车辆某一时间之前的所有数据
     * @param vehicleId
     * @param time
     * @return
     */
    List<TrainTestData> findAllByVehicleIdBefore(Integer vehicleId, Long time);

    /**
     * 获取某车辆某一时间之后的所有数据
     * @param vehicleId
     * @param time
     * @return
     */
    List<TrainTestData> findAllByVehicleIdAfter(Integer vehicleId, Long time);

    /**
     * 获取某车辆某一段时间之间的所有数据
     * @param vehicleId
     * @param sTime
     * @param eTime
     * @return
     */
    List<TrainTestData> findAllByVehicleIdBetween(Integer vehicleId, Long sTime, Long eTime);

    /**
     * 保存数据
     * @param trainTestData
     */
    void save(TrainTestData trainTestData);
}
