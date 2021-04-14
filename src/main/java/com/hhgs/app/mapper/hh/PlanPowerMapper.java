package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.PlanPower;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("hhTemplate")
public interface PlanPowerMapper {

    PlanPower queryByDateAndPlantCode(@Param("date") String date,@Param("plantCode") int plantCode);
}
