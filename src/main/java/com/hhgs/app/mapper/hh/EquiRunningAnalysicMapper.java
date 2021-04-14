package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.EquiRunningAnalysic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("hhTemplate")
public interface EquiRunningAnalysicMapper {

    EquiRunningAnalysic queryByPlantCodeAndCreateDate(@Param("plantCode") int plantCode, @Param("date") String date);

    void insert(EquiRunningAnalysic equiRunningAnalysic);

    void update(EquiRunningAnalysic equiRunningAnalysic);
}
