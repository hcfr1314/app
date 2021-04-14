package com.hhgs.app.service;

import com.hhgs.app.model.DO.SolarEnergy;

public interface SolarEnergyService {

    SolarEnergy queryByPlantCodeAndCreateDate(int plantCode, String date);

    void insert(SolarEnergy solarEnergy);

    void update(SolarEnergy solarEnergy);
}
