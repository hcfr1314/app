package com.hhgs.app.mapper.hh;

import java.util.List;

import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("hhTemplate")
@Repository
public interface PlantMappingMapper {

    /**
     * 获取所有场站名称列表,其中包含topic对应的场站名称，基础表名称，历史表信息等
     * @return
	 * @param idList
     */
    List<AlarmPlantMapping> getAllPlantMapping(@Param("list") List<Integer> idList);


    AlarmPlantMapping getByPlantName(@Param("plantName") String plantName);
}
