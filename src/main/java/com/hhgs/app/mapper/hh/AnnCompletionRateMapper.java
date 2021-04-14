package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.AnnualCompletionRate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("hhTemplate")
public interface AnnCompletionRateMapper {

    AnnualCompletionRate queryByPlantCodeAndCreateDate(int plantCode, String date);

    void insert(AnnualCompletionRate annCompletionRate);

    void update(AnnualCompletionRate annCompletionRate);
}
