package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.ActivePowerAndIrradiance;
import com.hhgs.app.model.DO.IrradiationGeneration;
import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.model.DO.WebappPoint;
import com.hhgs.app.model.DO.EquipmentRunning;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface RunningMapper {
    List<ActivePowerAndIrradiance> queryActivePowerAndIrradiance(@Param("plantCode") int plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    List<IrradiationGeneration> queryIrradiationGeneration(@Param("plantCode") int plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 查询所有光伏场站信息
     * @return
     */
    List<PlantInfo> queryAllPlantInfo();

    /**
     * 根据场站编码查询场站时候四测点信息
     * @param plantCode
     * @return
     */
    List<WebappPoint> queryPointInfo(@Param("plantCode") int plantCode);

    /**
     * 设备运行指标数据入库
     * @param equipmentRunning
     */
    void saveEquipmentRunningIndex(@Param("equipmentRunning")EquipmentRunning equipmentRunning);

    /**
     * 查询有功功率和辐照度（按小时展示）
     * @param plantCode
     * @param startTime
     * @param endTime
     * @return
     */
    List<EquipmentRunning> queryActPowerAndIrr(@Param("plantCode") int plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
