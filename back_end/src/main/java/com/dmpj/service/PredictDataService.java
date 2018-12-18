package com.dmpj.service;

import com.dmpj.entity.PredictData;
import com.dmpj.enums.ModelEnum;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 22:04
 */
public interface PredictDataService {
    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    List<PredictData> findAllById(Integer id);

    /**
     * 根据id列表获取信息
     * @param ids
     * @return
     */
    List<PredictData> findAllByIdInAndType(List<Integer> ids, Integer type);

    /**
     * 根据type获取信息
     * @param type
     * @return
     */
    List<PredictData> findAllByType(Integer type);

    /**
     * 根据type列表获取信息
     * @param types
     * @return
     */
    List<PredictData> findAllByTypesIn(List<Integer> types);

    /**
     * 根据id和type获取数据
     * @param id
     * @param type
     * @return
     */
    PredictData findOneByIdAndType(Integer id, Integer type);

    /**
     * 保存数据
     * @param predictData
     */
    void save(PredictData predictData);
}
