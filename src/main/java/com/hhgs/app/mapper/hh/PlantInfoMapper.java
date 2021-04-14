package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.model.DO.user.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface PlantInfoMapper {

    List<PlantInfo> getPlantInfoList(@Param("plantType") int plantType, @Param("list") List<Group> groups);

    List<PlantInfo> getAllList();
}
