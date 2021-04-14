package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.EnergyComsume;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface EnergyComsumeMapper {
    List<EnergyComsume> monthOfRate(@Param("plantCode") int plantCode, @Param("date") String date);
}
