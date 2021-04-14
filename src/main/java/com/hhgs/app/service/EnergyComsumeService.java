package com.hhgs.app.service;

import com.hhgs.app.model.DO.EnergyComsume;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EnergyComsumeService {
    Map<String,Object> monthOfRate(int plantCode, String date);

    EnergyComsume queryDataByTimeAndPlantCode(String date, int plantCode);

    void addData(EnergyComsume energyComsume);

    void updateData( EnergyComsume energyComsume);
}
