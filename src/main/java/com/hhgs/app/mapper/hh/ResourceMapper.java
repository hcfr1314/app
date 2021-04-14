package com.hhgs.app.mapper.hh;


import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.model.DO.IrradiationGeneration;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface ResourceMapper {

    List<EquipmentRunning> queryIrradiance(@Param("plantCode") int plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<IrradiationGeneration> queryIrradiationAndSunHours(@Param("plantCode") int plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
