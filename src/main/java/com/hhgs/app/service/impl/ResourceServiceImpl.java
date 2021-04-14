package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.InfluxDBDataObjectMapper;
import com.hhgs.app.mapper.hh.PlantShowsMapper;
import com.hhgs.app.mapper.hh.PointMapper;
import com.hhgs.app.mapper.hh.ResourceMapper;
import com.hhgs.app.model.DO.*;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.service.ResourceService;
import com.hhgs.app.util.DateUtil;
import com.hhgs.app.util.SelectDataFromInfluxdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {


    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    private InfluxDBDataObjectMapper influxDBDataObjectMapper;

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private PointMapper pointMapper;

    @Override
    public Map<String, HbaseResult> queryIrradiance(int plantCode, String currentDate) {

        Map<String, HbaseResult> resultMap = new HashMap<>();
        List<PlantTable> plantList = plantShowsMapper.getAllPlantName();
        List<String> nameList = plantList.stream().filter(e -> "1".equals(e.getPlantType())).map(p -> p.getPlantName()).collect(Collectors.toList());
        List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(nameList);

        String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getPlantCode() == plantCode).collect(Collectors.toList()).get(0).getDataObjectName();
        List<Points> points = pointMapper.queryIndicatorGrapIndex(plantCode);

        String startTime = DateUtil.getTime(currentDate + " 00:00:00")+"";
        String endTime = DateUtil.getTime(currentDate + " 23:59:59")+"";
       /* Date startTime = DateUtil.getDateTime(currentDate + " 00:00:00");
        Date endTime = DateUtil.getDateTime(currentDate + " 23:59:59");
        List<EquipmentRunning> equipmentRunnings = resourceMapper.queryIrradiance(plantCode, startTime, endTime);

        Map<String, List<EquipmentRunning>> listMap = equipmentRunnings.stream().collect(Collectors.groupingBy(EquipmentRunning::getIndexName));

        List<EquipmentRunning> equipmentRunningList  = new ArrayList<>();
        listMap.forEach(((s, equipmentRunnings1) -> {
            if(s.equals("辐照度")) {
                equipmentRunningList.addAll(equipmentRunnings1);
            }
        }));*/

        points.forEach(point -> {
            if(point.getOrgType() == 4) {
                long orgId = point.getOrgId();
                HbaseResult dataFromInfluxdb = SelectDataFromInfluxdb.getDataFromInfluxdb(dataObjectName, startTime, endTime, orgId);
                if (dataFromInfluxdb != null) {
                    resultMap.put(point.getDescription(), dataFromInfluxdb);
                }
            }
        } );

        return resultMap;
    }

    @Override
    public List<IrradiationGeneration> queryIrradiationAndSunHours(int plantCode, String currentMonth) {
        Date beginTime = DateUtil.getBeginTime(currentMonth);
        Date endTime = DateUtil.getEndTime(currentMonth);
        List<IrradiationGeneration> irradiations = resourceMapper.queryIrradiationAndSunHours(plantCode, beginTime, endTime);
        return irradiations;
    }
}
