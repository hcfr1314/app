package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.PlanPowerMapper;
import com.hhgs.app.mapper.hh.PowerIndicatorMapper;
import com.hhgs.app.model.DO.PlanPower;
import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.VO.IndexPlanRate;
import com.hhgs.app.service.PowerIndicatorService;
import com.hhgs.app.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class PowerIndicatorServiceImpl implements PowerIndicatorService {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static DateTimeFormatter yearAndMontFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

    @Autowired
    private PowerIndicatorMapper powerIndicatorMapper;

    @Autowired
    private PlanPowerMapper planPowerMapper;

    @Override
    public IndexPlanRate planRate(int plantCode) {
        LocalDate now = LocalDate.now();
        //查询昨天数据
        String yesterday = dateTimeFormatter.format(now.plusDays(-1));
        PowerIndicator powerIndicator = powerIndicatorMapper.queryByCreateTime(yesterday, plantCode);
        if (powerIndicator != null) {
            IndexPlanRate result = new IndexPlanRate();
            result.setPowerOfDay(powerIndicator.getPowerOfDay());
            result.setPowerOfMonth(powerIndicator.getPowerOfMonth());
            //计算同比，环比值(稍后计算)---------------------------------------------------
            //上个月最后一天，和上上个月最后一天算环比
            String lastMonthStr = yearAndMontFormatter.format(now.plus(-1, ChronoUnit.MONTHS));
            String lastLastMonthStr = yearAndMontFormatter.format(now.plus(-2, ChronoUnit.MONTHS));
            String lastYearStr = yearAndMontFormatter.format(now.plus(-1, ChronoUnit.YEARS).plus(-1, ChronoUnit.MONTHS));

            //查询计划发电量
            PlanPower lastMonthPlanPW = planPowerMapper.queryByDateAndPlantCode(lastMonthStr, plantCode);
            PlanPower lastLastMonthPlanPW = planPowerMapper.queryByDateAndPlantCode(lastLastMonthStr, plantCode);
            PlanPower lastYearPlanPW = planPowerMapper.queryByDateAndPlantCode(lastYearStr, plantCode);

            //查询月累计发电量
            lastMonthStr = dateTimeFormatter.format(now.plus(-1, ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth()));
            lastLastMonthStr = dateTimeFormatter.format(now.plus(-2, ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth()));
            lastYearStr = dateTimeFormatter.format(now.plus(-1, ChronoUnit.MONTHS).plus(-1, ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth()));

            PowerIndicator lastMonthPW = powerIndicatorMapper.queryByCreateTime(lastMonthStr, plantCode);
            PowerIndicator lastLastMonthPW = powerIndicatorMapper.queryByCreateTime(lastLastMonthStr, plantCode);
            PowerIndicator lastYearPW = powerIndicatorMapper.queryByCreateTime(lastYearStr, plantCode);

            //月发电量完成率(并且计划不能为0)
            double lastRate = 0.0D;
            double lastLastRate = 0.0D;
            double lastYearRate = 0.0D;

            if (lastMonthPlanPW == null || lastMonthPlanPW.getPlanPw() == 0 || lastMonthPW == null) {
                result.setmOM("--");
                result.setyOM("--");
                result.setLastRate("--");
                return result;
            } else {
                lastRate = lastMonthPW.getPowerOfMonth() / lastMonthPlanPW.getPlanPw();
                result.setLastRate(String.valueOf(lastRate));
            }

            if (lastLastMonthPlanPW == null || lastLastMonthPlanPW.getPlanPw() == 0 || lastLastMonthPW == null) {
                result.setmOM("--");
            } else {
                lastLastRate = lastLastMonthPW.getPowerOfMonth() / lastLastMonthPlanPW.getPlanPw();
            }


            if (lastYearPlanPW == null || lastYearPlanPW.getPlanPw() == 0) {
                result.setyOM("--");
            } else {
                lastYearRate = lastYearPW.getPowerOfMonth() / lastYearPlanPW.getPlanPw();
            }

            //同比增长
            if ("--".equals(result.getmOM())) {
                double mOM = lastRate - lastYearRate;
                result.setmOM(String.valueOf(mOM));
            }

            //环比增长
            if ("--".equals(result.getyOM())) {
                double yOM = lastRate - lastLastRate;
                result.setyOM(String.valueOf(yOM));
            }

            return result;
        }

        return null;
    }

    @Override
    public Map<String, List<Double>> powerOfMonth(int plantCode, String date) {

        //查询本月数据
        List<PowerIndicator> thisMonthList = powerIndicatorMapper.queryMPWByCreateTime(plantCode, date);

        //查询上个月数据
        LocalDate now = LocalDate.parse(date + "-01");
        int dayOfMonth = now.lengthOfMonth() + 1;
        String lastMonth = yearAndMontFormatter.format(now.plus(-1, ChronoUnit.MONTHS));
        List<PowerIndicator> lastMonthList = powerIndicatorMapper.queryMPWByCreateTime(plantCode, lastMonth);

        //查询同期数据
        String lastYear = yearAndMontFormatter.format(now.plus(-1, ChronoUnit.YEARS));
        List<PowerIndicator> lastYearList = powerIndicatorMapper.queryMPWByCreateTime(plantCode, lastYear);

        if (thisMonthList != null && thisMonthList.size() > 0) {
            Map<String, List<Double>> map = new HashMap<>(3);
            List<Double> thisList = new ArrayList<>(dayOfMonth);
            List<Double> mOMList = new ArrayList<>(dayOfMonth);
            List<Double> yOMList = new ArrayList<>(dayOfMonth);
            for (int i = 0; i < dayOfMonth; i++) {
                thisList.add(0D);
                mOMList.add(0D);
                yOMList.add(0D);
            }

            thisMonthList.forEach(ele -> {
                Date createDate = ele.getCreateDate();
                int day = DateUtil.getDayOfMonth(createDate);
                thisList.set(day, ele.getPowerOfDay());
            });

            map.put("thisList", thisList);

            //计算环比(((本期-上期)/上期)*100)
            for (int i = 1; i < thisList.size(); i++) {
                double thisMonthData = thisList.get(i);
                double mOM = 0D;
                double yOM = 0D;
                for (int j = 0; j < lastMonthList.size(); j++) {
                    PowerIndicator lastMonthData = lastMonthList.get(j);
                    if (i == DateUtil.getDayOfMonth(lastMonthData.getCreateDate())) {
                        double lastMonthPower = lastMonthData.getPowerOfDay();
                        mOM = ((thisMonthData - lastMonthPower) / lastMonthPower) * 100;
                        mOMList.set(i, mOM);
                        break;
                    }
                }


                //计算同比(((本期-同期)/同期)*100)
                for (int j = 0; j < lastYearList.size(); j++) {
                    PowerIndicator lastYearData = lastYearList.get(j);
                    if (i == DateUtil.getDayOfMonth(lastYearData.getCreateDate())) {
                        double lastYearPower = lastYearData.getPowerOfDay();
                        yOM = ((thisMonthData - lastYearPower) / lastYearPower) * 100;
                        yOMList.set(i, yOM);
                        break;
                    }
                }
            }

            map.put("mOMList", mOMList);

            map.put("yOMList", yOMList);

            return map;
        }

        return null;
    }

    @Override
    public List<PowerIndicator> hour(int plantCode, String date) {
        //查询当前月的日利用小时数
        return powerIndicatorMapper.queryMPWByCreateTime(plantCode, date);
    }

    @Override
    public PowerIndicator queryByPlantCodeAndCreateDate(int plantCode, String date) {
        return powerIndicatorMapper.queryByPlantCodeAndCreateDate(plantCode, date);
    }

    @Override
    public void insert(PowerIndicator equiRunningAnalysic) {
        powerIndicatorMapper.insert(equiRunningAnalysic);
    }

    @Override
    public void update(PowerIndicator equiRunningAnalysic) {
        powerIndicatorMapper.update(equiRunningAnalysic);
    }


}
