package com.hhgs.app.service;

import com.hhgs.app.model.DO.AnnualCompletionRate;

public interface AnnCompletionRateService {

    AnnualCompletionRate queryByPlantCodeAndCreateDate(int plantCode, String date);

    void insert(AnnualCompletionRate annCompletionRate);

    void update(AnnualCompletionRate annCompletionRate);

}
