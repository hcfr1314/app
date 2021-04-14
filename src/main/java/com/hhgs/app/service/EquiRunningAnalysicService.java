package com.hhgs.app.service;
import com.hhgs.app.model.DO.EquiRunningAnalysic;

public interface EquiRunningAnalysicService {

    EquiRunningAnalysic queryByPlantCodeAndCreateDate(int plantCode, String date);

    void insert(EquiRunningAnalysic equiRunningAnalysic);

    void update(EquiRunningAnalysic equiRunningAnalysic);
}
