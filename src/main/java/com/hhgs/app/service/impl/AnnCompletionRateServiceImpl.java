package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.AnnCompletionRateMapper;
import com.hhgs.app.model.DO.AnnualCompletionRate;
import com.hhgs.app.service.AnnCompletionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnCompletionRateServiceImpl implements AnnCompletionRateService {

    @Autowired
    private AnnCompletionRateMapper annCompletionRateMapper;

    @Override
    public AnnualCompletionRate queryByPlantCodeAndCreateDate(int plantCode, String date) {
        return annCompletionRateMapper.queryByPlantCodeAndCreateDate(plantCode,date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(AnnualCompletionRate annCompletionRate) {
        annCompletionRateMapper.insert(annCompletionRate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AnnualCompletionRate annCompletionRate) {
        annCompletionRateMapper.update(annCompletionRate);
    }
}
