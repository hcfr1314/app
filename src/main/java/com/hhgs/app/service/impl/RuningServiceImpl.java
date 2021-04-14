package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.InfluxDBDataObjectMapper;
import com.hhgs.app.mapper.hh.PlantShowsMapper;
import com.hhgs.app.mapper.hh.PointMapper;
import com.hhgs.app.mapper.hh.RunningMapper;
import com.hhgs.app.model.DO.*;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.service.RuningService;
import com.hhgs.app.util.DateUtil;
import com.hhgs.app.util.SelectDataFromInfluxdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RuningServiceImpl implements RuningService {


    @Autowired
    RunningMapper runningMapper;


   /* @Override
    public List<ActivePowerAndIrradiance> queryActivePowerAndIrradiance(int plantCode, String currentMonth) {
        Date beginTime = DateUtil.getBeginTime(currentMonth);
        Date endTime = DateUtil.getEndTime(currentMonth);

        List<ActivePowerAndIrradiance> activePowerAndIrradiances = runningMapper.queryActivePowerAndIrradiance(plantCode, beginTime, endTime);
        return activePowerAndIrradiances;
    }
*/

    @Override
    public Map<String, String[]> queryActivePowerAndIrradiance(String id,String currentDate,String ipddr,String dbName) throws ParseException {
        Date beginTime = DateUtil.getInfluxdbStartTime(currentDate);
        Date endTime = DateUtil.getInfluxdbEndTime(currentDate);

        Map<String, String[]> data = SelectDataFromInfluxdb.getData(id, beginTime, endTime, ipddr, dbName);
        return data;
    }

    @Override
    public List<IrradiationGeneration> queryIrradiationGeneration(int plantCode, String currentMonth) {
        Date beginTime = DateUtil.getBeginTime(currentMonth);
        Date endTime = DateUtil.getEndTime(currentMonth);
        List<IrradiationGeneration> irradiationGenerations = runningMapper.queryIrradiationGeneration(plantCode, beginTime, endTime);
        return irradiationGenerations;
    }

    @Override
    public List<PlantInfo> queryAllPlantInfo() {

        List<PlantInfo> plantInfos = runningMapper.queryAllPlantInfo();
        return plantInfos;
    }

    @Override
    public List<WebappPoint> queryPointInfo(int plantCode) {
        List<WebappPoint> webappPoints = runningMapper.queryPointInfo(plantCode);
        return webappPoints;
    }

    @Override
    public Map<Integer, Map<Integer, List<WebappPoint>>> queryAllPoint() {

        Map<Integer, Map<Integer, List<WebappPoint>>> resultMap = new HashMap<>();
        List<PlantInfo> plantInfos = runningMapper.queryAllPlantInfo();
        plantInfos.forEach(ele ->
                {
                    List<WebappPoint> webappPoints = runningMapper.queryPointInfo(ele.getPlantCode());
                    if(webappPoints != null && webappPoints.size()!= 0 ) {
                        Map<Integer, List<WebappPoint>> pointResultMap = webappPoints.stream().collect(Collectors.groupingBy(WebappPoint::getOrgType));
                        resultMap.put(ele.getPlantCode(),pointResultMap);
                    }
                }
        );
        return resultMap;
    }

    @Override
    public void saveEquipmentRunningIndex(EquipmentRunning equipmentRunning) {
        runningMapper.saveEquipmentRunningIndex(equipmentRunning);
    }


    @Autowired
    private InfluxDBDataObjectMapper influxDBDataObjectMapper;

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private PointMapper pointMapper;

    @Override
    public Map<String, HbaseResult> queryActPowerAndIrr(int plantCode,String currentDate) {

        Map<String, HbaseResult> resultMap = new HashMap<>();
        List<PlantTable> plantList = plantShowsMapper.getAllPlantName();
        List<String> nameList = plantList.stream().filter(e -> "1".equals(e.getPlantType())).map(p -> p.getPlantName()).collect(Collectors.toList());
        List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(nameList);

        String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getPlantCode() == plantCode).collect(Collectors.toList()).get(0).getDataObjectName();
        List<Points> points = pointMapper.queryIndicatorGrapIndex(plantCode);

        String startTime = DateUtil.getTime(currentDate + " 00:00:00")+"";
        String endTime = DateUtil.getTime(currentDate + " 23:59:59")+"";

        /*List<EquipmentRunning> equipmentRunnings = runningMapper.queryActPowerAndIrr(plantCode, startTime, endTime);
        Map<String, List<EquipmentRunning>> listMap = equipmentRunnings.stream().collect(Collectors.groupingBy(EquipmentRunning::getIndexName));*/

        points.forEach(point -> {
            long orgId = point.getOrgId();
            HbaseResult dataFromInfluxdb = SelectDataFromInfluxdb.getDataFromInfluxdb(dataObjectName, startTime, endTime, orgId);
            if(dataFromInfluxdb != null) {
                resultMap.put(point.getDescription(),dataFromInfluxdb);
            }
        } );
        return resultMap;
    }
}
