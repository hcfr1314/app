package com.hhgs.app.task;

import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.model.DO.WebappPoint;
import com.hhgs.app.model.DO.hbase.*;
import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.service.*;
import com.hhgs.app.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 计算相关指标，定时计算
 */
//@Component
public class CalculationTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationTask.class);

    private static final DateFormat ONLY_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    private Map<Integer, List<HbaseObject>> hbaseObejcMap;

    private Map<Integer, Map<Integer, List<WebappPoint>>> webappPointMap;

    private Map<Integer, Map<Integer, List<HbasePoint>>> hbasePointMap;

    private Map<Integer, Class> wayMap = new HashMap<>();

    private Map<Integer, List<PlantInfo>> plantInfoMap;

    @Autowired
    private HbaseObjectService hbaseObjectService;

    @Autowired
    private HbasePointService hbasePointService;

    @Autowired
    private RuningService runingService;

    @Autowired
    private PlantInfoService plantInfoService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ApplicationContext context;


    @PostConstruct
    public void init() {

        //查询所有业务对象
        hbaseObejcMap = hbaseObjectService.queryAllHbaseObject();

        //查询所有点根据场站分组
        hbasePointMap = hbasePointService.getIdByPlantCodeAndIndexId();

        //查询所有实时测点信息
        webappPointMap = runingService.queryAllPoint();

        //各个场站的装机容量
        plantInfoMap = plantInfoService.getAllList();

    }

    /**
     * 整点执行定时任务
     */
    //@Scheduled(cron = "0 0 * * * ?")
    public void mainTask() {
        long endtime = System.currentTimeMillis();
        String start = String.valueOf(DateUtil.getDayStartTimestap());
        String end = String.valueOf(endtime);
      /*  hbasePointMap.forEach((plantCode, map) -> {
            String hbaseObjectName = hbaseObejcMap.get(plantCode).get(0).getDataObjectName();
            map.forEach((indexId, HbasePoints) -> {
                HbaseResult dataFromHbase = HbaseUtils.getDataFromHbase(hbaseObjectName, start, end, HbasePoints);
                LOGGER.info(hbaseObjectName+"---------------------------------");

                //当请求成功时
                if ("0".equals(dataFromHbase.getStatusCode())) {
                    List<String[]> dataRows = dataFromHbase.getData().getDataRows();
                    if (dataRows != null && dataRows.size() > 0) {
                        //获取装机容量的值
                        List<PlantInfo> plantInfos = plantInfoMap.get(plantCode);
                        CalaWay bean = (CalaWay) context.getBean(wayMap.get(plantCode));
                        double cap = BasicCalaUtils.div(plantInfos.get(0).getCapacity(), 10);
                        calaAndSaveToDB(indexId, dataRows, HbasePoints, cap, start, plantCode, bean);
                    }

                }
            });
        });*/

        //设备运行指标页面，有功功率和辐照度
        //每个场站下包含一堆指标测点信息
        webappPointMap.forEach(((plantCode, stringListMap) -> {
            String hbaseObjectName = hbaseObejcMap.get(plantCode).get(0).getDataObjectName();
            //根据类型遍历每种类型所包含的指标信息
            stringListMap.forEach(((orgType, webappPoints) -> {
                List<HbasePoint> hbasePowerRatePoints = new ArrayList<>();
                List<HbasePoint> hbaseIrradiancePoints = new ArrayList<>();

                //筛选有功功率和辐照度测点
                if (orgType == 1 || orgType == 4) {
                    HbasePoint hbasePoint = new HbasePoint();
                    hbasePoint.setId(webappPoints.get(0).getOrgId());
                    if (orgType == 1) {
                        hbasePowerRatePoints.add(hbasePoint);
                        HbaseResult dataFromHbase = HbaseUtils.getDataFromHbase(hbaseObjectName, start, end, hbasePowerRatePoints);
                        if (dataFromHbase != null && dataFromHbase.getData().getDataRows() != null && dataFromHbase.getData().getDataRows().size() > 0) {
                            saveEquipmentRunningIndex(webappPoints.get(0).getDescription(), plantCode, dataFromHbase);
                        }
                        hbasePowerRatePoints.clear();
                    } else if (orgType == 4) {

                        hbaseIrradiancePoints.add(hbasePoint);
                        HbaseResult dataFromHbase = HbaseUtils.getDataFromHbase(hbaseObjectName, start, end, hbaseIrradiancePoints);
                        if (dataFromHbase != null && dataFromHbase.getData().getDataRows() != null && dataFromHbase.getData().getDataRows().size() > 0) {
                            saveEquipmentRunningIndex(webappPoints.get(0).getDescription(),plantCode, dataFromHbase);
                        }
                        hbaseIrradiancePoints.clear();
                    }
                }
                LOGGER.info("此次执行用时" + (System.currentTimeMillis() - endtime));
            }));
        }));
    }

    /**
     * 计算方式
     *
     * @param indexId
     * @param dataRows
     */
    /*private void calaAndSaveToDB(Integer indexId, List<String[]> dataRows, List<HbasePoint> hbasePoints, double cap, String start, int plantCode, CalaWay calaWay) {

        List<String[]> initVal = dataRows.subList(0, hbasePoints.size());
        List<String[]> endVal = dataRows.subList(dataRows.size() - hbasePoints.size(), dataRows.size());
        double timesRate = hbasePoints.get(0).getTimesRate();

        Date date = new Date(Long.valueOf(start));
        String dateStr = ONLY_DATE_FORMATTER.format(date);

        String yesterdayStr = DateUtil.getYesterdayStr(Long.valueOf(start));
        switch (indexId) {
            case 1:
                //平均温度
                double avgTem = calaWay.calaAvgTmp(dataRows);
                //数据入库
                taskService.saveAvgTmp(avgTem, dateStr, plantCode, yesterdayStr);
                break;
            case 2:
                //平均风速
                double avgSpeed = calaWay.calaAvgSpeed(dataRows);
                taskService.saveAvgSpeed(avgSpeed, dateStr, plantCode, yesterdayStr);
                //数据入库
                break;
            case 3:
                //（末值-初值）    累加*倍率, 日上网电量，月上网电量，年上网电量
                double multi = calaWay.calaInternetPw(initVal, endVal, timesRate);
                taskService.saveInternetPw(multi, plantCode, dateStr, yesterdayStr);
                //数据入库
                break;
            case 4:
                //日利用小时（日累计发电量/装机容量）放在5中算
                //数据入库
                break;
            case 5:
                //计算方式2（日发电量）(这块要分三种情况计算珠玉一种算法，恰龙一种算法，其它一种算法)
                //日累计发电量
                double totalPower = calaWay.calaTotalPwAndHour(initVal, endVal, timesRate);
                //日利用小时（日累计发电量/装机容量）
                double hour = calaWay.calaHourOfDay(totalPower, cap);
                taskService.saveTotalPwAndHour(totalPower, hour, plantCode, dateStr, yesterdayStr);
                //数据入库
                break;
            case 6:
                Number[] numbers = calaWay.calaDailyMaxLoad(dataRows, cap);
                //三条数据入库
                if (numbers != null) {
                    taskService.saveDailyMaxLoad(plantCode, dateStr, (double) numbers[0], (long) numbers[1], (double) numbers[2]);
                }
                break;
            case 7:
                //在6中处理
                break;
            case 8:
                //在6中处理
                break;
            case 9:
                //日照小时
                double sunShineHours = calaWay.calaSunShineHours(dataRows);
                taskService.saveSunShineHours(plantCode, dateStr, yesterdayStr, sunShineHours);
                break;
            case 10:
                //日站用3585电量(末-初)*倍率
                double val_3585 = calaWay.calaVal_3585(initVal, endVal, timesRate);
                //数据入库
                taskService.saveVal_3585(val_3585, dateStr, plantCode, yesterdayStr);
                break;
            case 11:
                //日站用3590电量
                double val_3590 = calaWay.calaVal_3590(initVal, endVal, timesRate);
                //数据入库
                taskService.saveVal_3590(val_3590, dateStr, plantCode, yesterdayStr);
                break;
            case 12:
                //日站用3595电量
                double val_3595 = calaWay.calaVal_3595(initVal, endVal, timesRate);
                //数据入库
                taskService.saveVal_3595(val_3595, dateStr, plantCode, yesterdayStr);
                break;
            case 13:
                //日购网电量(日外来电电源电量+日返送电量)
                //放在16中计算
                //数据入库
                break;
            case 14:
                //辐照量(取测点末值)
                double irradtionVal = calaWay.calaIrradtionVal(dataRows);
                //数据入库
                taskService.saveIrradtionVal(plantCode, dateStr, yesterdayStr, irradtionVal);
                break;
            case 15:
                //日运行小时
                double hours = calaWay.calaHours(dataRows);
                //数据入库
                taskService.saveHours(plantCode, dateStr, yesterdayStr, hours);
                break;
            case 16:
                //日返送电量
                double reversePower = calaWay.calaReverserPower(initVal, endVal, timesRate);
                taskService.saveReverserPower(plantCode, dateStr, yesterdayStr, reversePower);
                break;
            case 17:
                //综合厂用电率,放在18中计算
                break;
            case 18:
                //日综合厂用电量（）
                taskService.saveFactoryPWAndRate(plantCode, dateStr, yesterdayStr);
                break;
            default:
                break;
        }
    }*/

    /**
     * 封装设备运行指标并入库
     *
     * @param pointDescription
     * @param dataFromHbase
     */
    private void saveEquipmentRunningIndex(String pointDescription, int plantCode, HbaseResult dataFromHbase) {

        EquipmentRunning equipmentRunning = new EquipmentRunning();
        List<String[]> dataRows = dataFromHbase.getData().getDataRows();
        String[] resultArray = dataRows.get(dataRows.size() - 1);
        String value = resultArray[1];
        long aLong = Long.parseLong(resultArray[2]);

        equipmentRunning.setIndexName(pointDescription);
        equipmentRunning.setIndexValue(value);
        equipmentRunning.setCreateTime(DateUtil.fromatTime(aLong));
        equipmentRunning.setPlantCode(plantCode);

        //数据入库
        runingService.saveEquipmentRunningIndex(equipmentRunning);
    }


}
