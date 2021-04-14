package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.EnergyComsume;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EnergyConsumptionMapper {

    /**
     * 根据场站id和时间范围查询数据
     * @return
     */
    EnergyComsume queryDataByTimeAndPlantCode(@Param("date") String date,@Param("plantCode") int plantCode);


    void addData(@Param("energyComsume") EnergyComsume energyComsume);

    void updateData(@Param("energyComsume") EnergyComsume energyComsume);
}
