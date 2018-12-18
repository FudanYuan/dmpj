package com.dmpj.controller;

import com.dmpj.entity.PredictData;
import com.dmpj.entity.TrainTestData;
import com.dmpj.enums.ModelEnum;
import com.dmpj.utils.EnumUtil;
import com.dmpj.utils.PredictByModelUtil;
import com.dmpj.utils.TimeUtil;
import com.dmpj.vo.*;
import com.dmpj.service.PredictDataService;
import com.dmpj.service.TrainTestDataService;
import com.dmpj.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 二手商品类目控制
 *
 * @Author: Jeremy
 * @Date: 2018/8/29 14:18
 */
@RestController
@RequestMapping
public class DataController {

    @Autowired
    private TrainTestDataService trainTestDataService;

    @Autowired
    private PredictDataService predictDataService;

    @CrossOrigin
    @PostMapping(value = "/getData")
    public ResultVO getData(@RequestParam("vId") Integer vId,
                            @RequestParam("mId") Integer mId,
                            @RequestParam(value = "type", defaultValue = "0") Integer type,
                            @RequestParam("sTime") Long sTime,
                            @RequestParam("eTime") Long eTime){
        System.out.println(vId + " " + mId + " " + new Date(sTime).toString() + " " + new Date(eTime));

        // 查询数据库
        List<TrainTestData> dataList = trainTestDataService.findAllByVehicleIdBetween(vId, sTime, eTime);

        List<EnergyDataVO> energyDataVOList = new ArrayList<>();
        List<TemperatureDataVO> temperatureDataVOList = new ArrayList<>();
        List<MileageDataVO> mileageDataVOList = new ArrayList<>();
        List<VoltageCurrentDataVO> voltageCurrentDataVOList = new ArrayList<>();

        // 获取真实数据集ID列表
        List<Integer> idList = new ArrayList<>();
        for (TrainTestData trainTestData : dataList){
            Integer id = trainTestData.getId();
            idList.add(id);

            // 获取充电结束的时间
            Long chargeStartTime = trainTestData.getChargeStartTime();
            Long chargeEndTime = trainTestData.getChargeEndTime();
            String time = TimeUtil.getTimeSlice(chargeStartTime, chargeEndTime);

            // 获取真实充电能量
            Double actualEnergy = trainTestData.getChargeEnergy();
            Double startSoc = trainTestData.getChargeStartSoc();
            Double endSoc = trainTestData.getChargeEndSoc();

            // 将该信息转化为VO
            EnergyDataVO energyDataVO = new EnergyDataVO();
            energyDataVO.setTime(time);
            energyDataVO.setStart_soc(startSoc);
            energyDataVO.setEnd_soc(endSoc);
            energyDataVO.setActual_energy(actualEnergy);
            // 将能量信息加入列表
            energyDataVOList.add(energyDataVO);

            // 获取温度数据
            Double maxTemp = trainTestData.getChargeMaxTemp();
            Double minTemp = trainTestData.getChargeMinTemp();
            // 将温度数据转化为VO
            TemperatureDataVO temperatureDataVO = new TemperatureDataVO();
            temperatureDataVO.setTime(time);
            temperatureDataVO.setMax_temperature(maxTemp);
            temperatureDataVO.setMin_temperature(minTemp);
            // 将温度数据信息加入列表
            temperatureDataVOList.add(temperatureDataVO);

            // 获取里程数据
            Double mileage = trainTestData.getMileage();
            // 将里程数据转化为VO
            MileageDataVO mileageDataVO = new MileageDataVO();
            mileageDataVO.setTime(chargeEndTime);
            mileageDataVO.setMileage(mileage);
            // 将温度数据信息加入列表
            mileageDataVOList.add(mileageDataVO);

            // 获取电压电流数据
            Double voltageStart = trainTestData.getChargeStartU();
            Double voltageEnd = trainTestData.getChargeEndU();
            Double currentStart = trainTestData.getChargeStartI();
            Double currentEnd = trainTestData.getChargeEndI();
            // 将电压-电流数据转化为VO
            VoltageCurrentDataVO voltageCurrentDataVO = new VoltageCurrentDataVO();
            voltageCurrentDataVO.setTime(time);
            voltageCurrentDataVO.setStart_voltage(voltageStart);
            voltageCurrentDataVO.setEnd_voltage(voltageEnd);
            voltageCurrentDataVO.setStart_current(currentStart);
            voltageCurrentDataVO.setEnd_current(currentEnd);
            // 将电压-电流数据信息加入列表
            voltageCurrentDataVOList.add(voltageCurrentDataVO);
        }

        List<PredictData> predictDataList = predictDataService.findAllByIdInAndType(idList, type);
        int count = 0;
        for (PredictData predictData : predictDataList){
            energyDataVOList.get(count++).setPredict_energy(PredictByModelUtil.getPredictDataByModel(predictData, EnumUtil.getByCode(mId, ModelEnum.class)));
        }
        DataVO dataVO = new DataVO();
        dataVO.setEnergyData(energyDataVOList);
        dataVO.setTemperatureData(temperatureDataVOList);
        dataVO.setMileageData(mileageDataVOList);
        dataVO.setVoltageCurrentData(voltageCurrentDataVOList);
        return ResultVOUtil.success(dataVO);
    }
}
