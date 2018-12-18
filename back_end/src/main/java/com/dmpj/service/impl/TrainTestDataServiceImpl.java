package com.dmpj.service.impl;

import com.dmpj.entity.TrainTestData;
import com.dmpj.repository.TrainTestDataRepository;
import com.dmpj.service.TrainTestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jeremy
 * @Integer: 2018/12/13 20:43
 */
@Service
public class TrainTestDataServiceImpl implements TrainTestDataService {

    @Autowired
    private TrainTestDataRepository repository;

    @Override
    public List<TrainTestData> findAllByVehicleId(Integer vehicleId) {
        return repository.findAllByVehicleId(vehicleId);
    }

    @Override
    public List<TrainTestData> findAllByVehicleIdBefore(Integer vehicleId, Long time) {
        return repository.findAllByVehicleIdAndChargeStartTimeBefore(vehicleId, time);
    }

    @Override
    public List<TrainTestData> findAllByVehicleIdAfter(Integer vehicleId, Long time) {
        return repository.findAllByVehicleIdAndChargeEndTimeAfter(vehicleId, time);
    }

    @Override
    public List<TrainTestData> findAllByVehicleIdBetween(Integer vehicleId, Long sTime, Long eTime) {
        return repository.findAllByVehicleIdAndChargeStartTimeAfterAndChargeEndTimeBefore(vehicleId, sTime, eTime);
    }

    @Override
    public void save(TrainTestData trainTestData) {
        repository.save(trainTestData);
    }
}
