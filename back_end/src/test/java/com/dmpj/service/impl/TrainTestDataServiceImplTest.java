package com.dmpj.service.impl;

import com.dmpj.entity.TrainTestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 20:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainTestDataServiceImplTest {

    @Autowired
    private TrainTestDataServiceImpl trainTestDataService;

    @Test
    public void save(){

        TrainTestData data = new TrainTestData();
        data.setVehicleId(2);
        data.setMileage(1020.12D);
        data.setChargeStartTime(1145103411L);
        data.setChargeEndTime(111312122L);
        data.setChargeStartSoc(100D);
        data.setChargeEndSoc(129D);
        data.setChargeStartU(10D);
        data.setChargeEndU(31.121D);
        data.setChargeStartI(10.1214D);
        data.setChargeEndI(31.3121D);
        data.setChargeMaxTemp(80.82D);
        data.setChargeMinTemp(20.21D);
        data.setChargeEnergy(89.39D);

        trainTestDataService.save(data);
    }

    @Test
    public void findAllByVehicleId() {
        List<TrainTestData> data = trainTestDataService.findAllByVehicleId(1);
        Assert.assertEquals(2, data.size());
    }

    @Test
    public void findAllByVehicleIdBefore() {
        List<TrainTestData> list = trainTestDataService.findAllByVehicleIdBefore(1, 1542529411L);
        System.out.println(new Date(1542529411).getTime());
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void findAllByVehicleIdAfter() {
        List<TrainTestData> list = trainTestDataService.findAllByVehicleIdAfter(1, 121212121L);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void findAllByVehicleIdBetween() {
        List<TrainTestData> list = trainTestDataService.findAllByVehicleIdBetween(4, 1510993411L, 1543393411L);
        System.out.println(list.size());
        Assert.assertNotEquals(0, list.size());
    }
}