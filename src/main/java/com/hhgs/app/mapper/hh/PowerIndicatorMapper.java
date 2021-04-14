package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.PowerIndicator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface PowerIndicatorMapper {

    PowerIndicator queryByCreateTime(@Param("date") String date, @Param("plantCode") int plantCode);

    List<PowerIndicator> queryMPWByCreateTime(@Param("plantCode") int plantCode, @Param("date") String date);

    PowerIndicator queryByPlantCodeAndCreateDate(@Param("plantCode") int plantCode, @Param("date") String date);

    void insert(PowerIndicator equiRunningAnalysic);

    void update(PowerIndicator equiRunningAnalysic);
}
