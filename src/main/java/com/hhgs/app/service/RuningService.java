package com.hhgs.app.service;

import com.hhgs.app.model.DO.IrradiationGeneration;
import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.model.DO.WebappPoint;
import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.model.DO.hbase.HbaseResult;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RuningService {

    /**
     * 获得有功功率和辐照度
     * @param plantCode
     * @param currentMonth
     * @return
     */
     //List<ActivePowerAndIrradiance> queryActivePowerAndIrradiance(int plantCode, String currentMonth);


    /**
     * 获得有功功率和辐照度
     * @param id
     * @param currentDate
     * @param ipddr
     * @param dbName
     * @return
     * @throws ParseException
     */
     Map<String, String[]> queryActivePowerAndIrradiance(String id, String currentDate, String ipddr, String dbName) throws ParseException;

    /**
     * 获得辐照量和发电量
     * @param plantCode
     * @param currentMonth
     * @return
     */
     List<IrradiationGeneration> queryIrradiationGeneration(int plantCode, String currentMonth);


    /**
     * 查询所有场站列表
     * @return
     */
    List<PlantInfo> queryAllPlantInfo();


    List<WebappPoint> queryPointInfo( int plantCode);


    Map<Integer, Map<Integer, List<WebappPoint>>> queryAllPoint();

    void saveEquipmentRunningIndex(EquipmentRunning equipmentRunning);

    Map<String, HbaseResult> queryActPowerAndIrr(int plantCode, String currentDate);
}
