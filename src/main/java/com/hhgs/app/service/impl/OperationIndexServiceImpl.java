package com.hhgs.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.app.mapper.hh.InfluxDBDataObjectMapper;
import com.hhgs.app.mapper.hh.OperationIndexMapper;
import com.hhgs.app.model.DO.InfluxDBDataObject;
import com.hhgs.app.model.DO.Points;
import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.DO.hbase.*;
import com.hhgs.app.model.VO.OperationIndex;
import com.hhgs.app.service.OperationIndexService;
import com.hhgs.app.service.PointService;
import com.hhgs.app.util.HbaseUtils;
import com.hhgs.app.util.HttpUtils;
import com.hhgs.app.util.LoginCheckUtils;
import com.hhgs.app.util.SelectDataFromInfluxdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OperationIndexServiceImpl implements OperationIndexService {

    private static final String URL = "http://172.28.8.25/avatar-datamanage-service/data/queryMetaObjData?metaObjectName=";

    private static final String SUCCESS_CODE = "0";

    @Autowired
    OperationIndexMapper operationIndexMapper;

    @Autowired
    private PointService pointService;

    @Autowired
    private InfluxDBDataObjectMapper influxDBDataObjectMapper;


    @Override
    public PowerIndicator getIndexValueFromElectrivityIndex(int plantCode) {
        PowerIndicator powerIndicator = operationIndexMapper.queryIndexValueByPlantCode(plantCode);
        return powerIndicator;
    }

    @Override
    public OperationIndex getDateByTypeAndCode(int plantCode, int plantType, String token) {
        //查询当天基础数据
        List<OperationIndex> list = operationIndexMapper.getData(plantCode, plantType);

        OperationIndex result = null;
        if (list != null && list.size() > 0) {
            result = new OperationIndex();
            double allInstalledCapacity = list.stream().mapToDouble(OperationIndex::getInstalledCapacity).sum();
            double allCurrentCeneration = list.stream().mapToDouble(OperationIndex::getCurrentCeneration).sum();
            double allMonthAccCeneration = list.stream().mapToDouble(OperationIndex::getMonthAccCeneration).sum();
            double allYearAccCeneration = list.stream().mapToDouble(OperationIndex::getYearAccCeneration).sum();

            //整体赋值
            result.setAllInstalledCapacity(allInstalledCapacity);
            result.setAllCurrentCeneration(allCurrentCeneration);
            result.setAllMonthAccCeneration(allMonthAccCeneration);
            result.setAllYearAccCeneration(allYearAccCeneration);

            List<OperationIndex> filterList = list.stream().filter(ele ->
                    plantCode == ele.getPlantCode()
            ).collect(Collectors.toList());

            OperationIndex ele = filterList.get(0);

            //根据场站类型查询各个场站的实时测点
            List<Points> points = pointService.queryByType(plantType);

            List<String> stationNames = new ArrayList<>();

            //通过场站名称查询对应数据对象
            points.stream().collect(Collectors.groupingBy(Points::getPlantName)).forEach((stationName, val) -> {
                stationNames.add(stationName);
            });

            List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(stationNames);


            //去请求查询数据
            points.forEach(p -> {
                String orgId = String.valueOf(p.getOrgId());
                String stationName = p.getPlantName();
                //获取数据对象名称
                String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getStationName().equals(stationName)).collect(Collectors.toList()).get(0).getDataObjectName();

                String data = getData(dataObjectName, p);

                System.out.println("-------------------------------------" + p.getOrgId());

                if (data != null && data.length() > 0) {
                    p.setValue(Double.parseDouble(data));
                } else {
                    p.setValue(0);
                }
            });

            //平均风速和平均温度根据plantType选一个
            if (plantType == 1) {
                double allAvg = points.stream().filter(p -> p.getOrgType() == 3).mapToDouble(Points::getValue).average().getAsDouble();
                double singleAvg = points.stream().filter(p -> p.getPlantCode() == plantCode && p.getOrgType() == 3).collect(Collectors.toList()).get(0).getValue();
                result.setAllAverageTemperature(allAvg);
                result.setAverageTemperature(singleAvg);
            } else if (plantType == 2) {
                double allAvg = points.stream().filter(p -> p.getOrgType() == 2).mapToDouble(Points::getValue).average().getAsDouble();
                double singleAvg = points.stream().filter(p -> p.getPlantCode() == plantCode && p.getOrgType() == 2).collect(Collectors.toList()).get(0).getValue();
                result.setAllAvgWindSpeed(allAvg);
                result.setAvgWindSpeed(singleAvg);
            } else {
                return null;
            }

            //实发功率（实时值）

            //全部风电场or全部光伏场站
            double sum = points.stream().filter(p -> p.getOrgType() == 1).mapToDouble(Points::getValue).sum();
            result.setAllPw(sum);

            //单个场站
            double singleSum = points.stream().filter(p -> p.getPlantCode() == plantCode && p.getOrgType() == 1).collect(Collectors.toList()).get(0).getValue();
            result.setSinglePw(singleSum);

            //赋值单个场站的值
            result.setInstalledCapacity(ele.getInstalledCapacity());
            result.setCurrentCeneration(ele.getCurrentCeneration());
            result.setMonthAccCeneration(ele.getMonthAccCeneration());
            result.setYearAccCeneration(ele.getYearAccCeneration());

            return result;
        }
        return null;
    }

    public String getData(String objectName, Points point) {

        //构造起始时间和结束时间
        long endTime = System.currentTimeMillis();
        long startTime = endTime - 3 * 60 * 1000;
        HbaseResult dataFromHbase = getDataFromInfluxdb(objectName, String.valueOf(startTime), String.valueOf(endTime), point.getOrgId());
        Data data = dataFromHbase.getData();
        if (data == null) {
            return null;
        }
        List<String[]> dataRows = data.getDataRows();
        if (data != null && dataRows != null && dataRows.size() > 0) {
            String[] strings = data.getDataRows().get(0);
            return strings[2];
        }
        return null;
    }


    /**
     * 调用阿凡达接口，从influxdb获取数据
     *
     * @param dataObjectName
     * @param startTime
     * @param endTime
     * @return
     */
    public HbaseResult getDataFromInfluxdb(String dataObjectName, String startTime, String endTime, long id) {

        String p = "{'starttime': '" + startTime + "','id':'" + id + "','alignpoint':'" + 60000 + "','alignoffset':'" + 60000 + "','endtime': '" + endTime + "'}";

        QueryObjectData param = new QueryObjectData(p, null);

        //调用阿凡达接口，首先需要获取token
        LoginCheckResult result = null;
        try {
            result = LoginCheckUtils.LoginCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!SUCCESS_CODE.equals(result.getStatusCode())) {
            return null;
        }

        String token = result.getData();

        //调用阿凡达接口
        String json = HttpUtils.getInstance().doPostByToken(URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }


}
