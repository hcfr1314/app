package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.SolarEnergy;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("hhTemplate")
public interface SolarEnergyMapper {

    SolarEnergy queryByPlantCodeAndCreateDate(@Param("plantCode") int plantCode, @Param("date") String date);

    void insert(SolarEnergy solarEnergy);

    void update(SolarEnergy solarEnergy);
}
