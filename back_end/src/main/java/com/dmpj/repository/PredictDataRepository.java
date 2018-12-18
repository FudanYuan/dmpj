package com.dmpj.repository;


import com.dmpj.entity.CombinePrimaryKey;
import com.dmpj.entity.PredictData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 22:00
 */
public interface PredictDataRepository extends JpaRepository<PredictData, CombinePrimaryKey> {

    List<PredictData> findById(Integer id);

    List<PredictData> findAllByIdIn(List<Integer> ids);

    List<PredictData> findAllByType(Integer type);

    List<PredictData> findAllByTypeIn(List<Integer> type);

    PredictData findByIdAndType(Integer id, Integer type);

    List<PredictData> findAllByIdInAndType(List<Integer> types, Integer type);

}
