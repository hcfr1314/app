package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.EnergyComsumeMapper;
import com.hhgs.app.mapper.hh.EnergyConsumptionMapper;
import com.hhgs.app.model.DO.EnergyComsume;
import com.hhgs.app.model.DO.StandardValue;
import com.hhgs.app.service.EnergyComsumeService;
import com.hhgs.app.service.StandardValueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EnergyComsumeServiceImpl implements EnergyComsumeService {

    @Autowired
    private EnergyComsumeMapper energyComsumeMapper;

    @Autowired
    private EnergyConsumptionMapper energyConsumptionMapper;

    @Autowired
    private StandardValueService standardValueService;

    @Override
    public Map<String, Object> monthOfRate(int plantCode, String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("standard", null);
        map.put("comsumeEle", null);
        List<StandardValue> list = standardValueService.getStandardValueByCode(plantCode);
        List<EnergyComsume> comsumeList = energyComsumeMapper.monthOfRate(plantCode, date);
        if (list != null && list.size() > 0) {
            map.put("standard", list.get(0));
        }

        if (comsumeList != null && comsumeList.size() > 0) {
            map.put("comsumeEle", comsumeList);
        }
        return map;
    }

    @Override
    public EnergyComsume queryDataByTimeAndPlantCode(String date, int plantCode) {
        EnergyComsume energyComsume = energyConsumptionMapper.queryDataByTimeAndPlantCode(date, plantCode);
        return energyComsume;
    }

    @Override
    @Transactional
    public void addData(EnergyComsume energyComsume) {
        energyConsumptionMapper.addData(energyComsume);
    }

    @Override
    @Transactional
    public void updateData(EnergyComsume energyComsume) {
        energyConsumptionMapper.updateData(energyComsume);
    }
}
