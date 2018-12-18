package com.dmpj.service.impl;

import com.dmpj.entity.PredictData;
import com.dmpj.repository.PredictDataRepository;
import com.dmpj.service.PredictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 22:12
 */
@Service
public class PredictDataServiceImpl implements PredictDataService {
    @Autowired
    private PredictDataRepository repository;

    @Override
    public List<PredictData> findAllById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PredictData> findAllByIdInAndType(List<Integer> ids, Integer type) {
        return repository.findAllByIdInAndType(ids, type);
    }

    @Override
    public List<PredictData> findAllByType(Integer type) {
        return repository.findAllByType(type);
    }

    @Override
    public List<PredictData> findAllByTypesIn(List<Integer> types) {
        return repository.findAllByTypeIn(types);
    }

    @Override
    public PredictData findOneByIdAndType(Integer id, Integer type) {
        return repository.findByIdAndType(id, type);
    }

    @Override
    public void save(PredictData predictData) {
        repository.save(predictData);
    }
}
