package com.dmpj.service.impl;

import com.dmpj.entity.PredictData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 22:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PredictDataServiceImplTest {

    @Autowired
    private PredictDataServiceImpl predictDataService;

    @Test
    public void findAllById() {
        List<PredictData> predictData = predictDataService.findAllById(40003);
        Assert.assertNotEquals(0, predictData.size());
    }

    @Test
    public void findAllByIdInAndType() {
        List<Integer> list = new ArrayList<>();
        list.add(40000);
        list.add(40002);
        List<PredictData> predictData = predictDataService.findAllByIdInAndType(list, 4);
        Assert.assertEquals(predictData.size(), 2);
    }

    @Test
    public void save() {
        PredictData predictData = new PredictData();
        predictData.setId(1);
        predictData.setABR(221.23D);
        predictData.setType(1);

        predictDataService.save(predictData);
    }
}