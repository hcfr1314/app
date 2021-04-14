package com.hhgs.app.service;

import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.VO.IndexPlanRate;

import java.util.List;
import java.util.Map;

/**
 * 电量指标Service
 */
public interface PowerIndicatorService {

    IndexPlanRate planRate(int plantName);


    Map<String, List<Double>> powerOfMonth(int plantCode, String date);


    List<PowerIndicator> hour(int plantCode, String date);


    PowerIndicator queryByPlantCodeAndCreateDate(int plantCode, String date);

    void insert(PowerIndicator equiRunningAnalysic);

    void update(PowerIndicator equiRunningAnalysic);
}
