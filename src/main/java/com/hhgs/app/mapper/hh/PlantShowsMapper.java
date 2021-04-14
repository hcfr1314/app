package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.PlantTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface PlantShowsMapper {



    /**
     * 获取所有场站名称列表
     * @return
     */
    List<PlantTable> getAllPlantName();

}
