package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.VO.OperationIndex;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface OperationIndexMapper {


    /**
     * 获取电量指标数据
     * @param plantCode
     * @return
     */
    PowerIndicator queryIndexValueByPlantCode(@Param("plantCode") int plantCode);

    List<OperationIndex> getData(@Param("plantCode") int plantCode,@Param("plantType") int plantType);
}
