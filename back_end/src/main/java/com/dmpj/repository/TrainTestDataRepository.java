package com.dmpj.repository;

import com.dmpj.entity.TrainTestData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 20:32
 */
public interface TrainTestDataRepository extends JpaRepository<TrainTestData, Integer> {

    List<TrainTestData> findAllByVehicleId(Integer vehicleId);

    List<TrainTestData> findAllByVehicleIdAndChargeStartTimeBefore(Integer vehicleId, Long chargeStartTime);

    List<TrainTestData> findAllByVehicleIdAndChargeEndTimeAfter(Integer vehicleId, Long chargeEndTime);

    List<TrainTestData> findAllByVehicleIdAndChargeStartTimeAfterAndChargeEndTimeBefore(Integer vehicleId, Long chargeStartTime, Long chargeEndTime);
}
