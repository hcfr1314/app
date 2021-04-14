package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.SolarEnergyMapper;
import com.hhgs.app.model.DO.SolarEnergy;
import com.hhgs.app.service.SolarEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolarEnergyServiceImpl implements SolarEnergyService {

    @Autowired
    private SolarEnergyMapper solarEnergyMapper;

    @Override
    public SolarEnergy queryByPlantCodeAndCreateDate(int plantCode, String date) {
        return solarEnergyMapper.queryByPlantCodeAndCreateDate(plantCode,date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SolarEnergy solarEnergy) {
        solarEnergyMapper.insert(solarEnergy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SolarEnergy solarEnergy) {
        solarEnergyMapper.update(solarEnergy);
    }
}
