package com.hhgs.app.service;

import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.model.DO.IrradiationGeneration;
import com.hhgs.app.model.DO.hbase.HbaseResult;

import java.util.List;
import java.util.Map;

public interface ResourceService {


    /**
     * 查询辐照度和日照小时
     * @param plantCode
     * @param currentDate
     * @return
     */
    Map<String, HbaseResult> queryIrradiance(int plantCode, String currentDate);


    /**
     * 查询辐照量
     * @param plantCode
     * @param currentMonth
     * @return
     */
    List<IrradiationGeneration> queryIrradiationAndSunHours(int plantCode, String currentMonth);
}
