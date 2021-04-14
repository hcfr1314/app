package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.EquiRunningAnalysicMapper;
import com.hhgs.app.model.DO.EquiRunningAnalysic;
import com.hhgs.app.service.EquiRunningAnalysicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquiRunningAnalysicServiceImpl implements EquiRunningAnalysicService {

    private EquiRunningAnalysicMapper equiRunningAnalysicMapper;


    @Override
    public EquiRunningAnalysic queryByPlantCodeAndCreateDate(int plantCode, String date) {
        return equiRunningAnalysicMapper.queryByPlantCodeAndCreateDate(plantCode,date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(EquiRunningAnalysic equiRunningAnalysic) {
        equiRunningAnalysicMapper.insert(equiRunningAnalysic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EquiRunningAnalysic equiRunningAnalysic) {
        equiRunningAnalysicMapper.update(equiRunningAnalysic);
    }
}
