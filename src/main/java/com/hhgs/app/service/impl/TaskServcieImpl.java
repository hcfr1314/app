package com.hhgs.app.service.impl;

import com.hhgs.app.model.DO.EquiRunningAnalysic;
import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.DO.SolarEnergy;
import com.hhgs.app.service.EquiRunningAnalysicService;
import com.hhgs.app.model.DO.EnergyComsume;
import com.hhgs.app.service.EnergyComsumeService;
import com.hhgs.app.service.PowerIndicatorService;
import com.hhgs.app.service.SolarEnergyService;
import com.hhgs.app.service.TaskService;
import com.hhgs.app.util.BasicCalaUtils;
import com.hhgs.app.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TaskServcieImpl implements TaskService {

    @Autowired
    private SolarEnergyService solarEnergyService;

    @Autowired
    private EnergyComsumeService energyComsumeService;

    @Autowired
    private PowerIndicatorService powerIndicatorService;

    @Autowired
    private EquiRunningAnalysicService equiRunningAnalysicService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAvgTmp(double avgTem, String dateStr, int plantCode, String yesterdayStr) {
        SolarEnergy solarEnergy = querySolarEnergy(plantCode, dateStr);
        if (solarEnergy == null) {
            SolarEnergy newEntity = new SolarEnergy();
            SolarEnergy solarEnergy1 = solarEnergyService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
            BeanUtils.copyProperties(solarEnergy1, newEntity);
            newEntity.setCreateDate(new Date());
            newEntity.setPlantCode(plantCode);
            newEntity.setAvgTemperature(avgTem);
            solarEnergyService.insert(newEntity);
            return;
        }

        solarEnergy.setAvgTemperature(avgTem);
        solarEnergyService.update(solarEnergy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAvgSpeed(double avgSpeed, String dateStr, int plantCode, String yesterdayStr) {
        SolarEnergy solarEnergy = querySolarEnergy(plantCode, dateStr);
        if (solarEnergy == null) {
            SolarEnergy newEntity = new SolarEnergy();
            SolarEnergy solarEnergy1 = solarEnergyService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
            BeanUtils.copyProperties(solarEnergy1, newEntity);
            newEntity.setCreateDate(new Date());
            newEntity.setPlantCode(plantCode);
            newEntity.setAvgWindSpeed(avgSpeed);
            solarEnergyService.insert(newEntity);
            return;
        }

        solarEnergy.setAvgWindSpeed(avgSpeed);
        solarEnergyService.update(solarEnergy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInternetPw(double multi, int plantCode, String dateStr, String yesterdayStr) {
        PowerIndicator thisData = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        PowerIndicator lastData = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
        if (thisData == null) {
            PowerIndicator newData = new PowerIndicator();
            BeanUtils.copyProperties(lastData, newData);
            int firstDay = DateUtil.isFirstDay(dateStr);
            if (firstDay == 1) {
                //月的第一天
                newData.setInternetPwOfDay(multi);
                newData.setInternetPwOfMonth(multi);
                newData.setInternetPwOfYear(newData.getInternetPwOfYear() + multi);
            } else if (firstDay == 0) {
                newData.setInternetPwOfDay(multi);
                newData.setInternetPwOfMonth(multi);
                newData.setInternetPwOfYear(multi);
            } else {  //其它时间
                newData.setInternetPwOfDay(newData.getInternetPwOfDay() + multi);
                newData.setInternetPwOfMonth(newData.getInternetPwOfMonth() + multi);
                newData.setInternetPwOfYear(newData.getInternetPwOfYear() + multi);
            }

            newData.setCreateDate(new Date());
            newData.setPlantCode(plantCode);
            powerIndicatorService.insert(newData);
            return;
        }

        thisData.setInternetPwOfDay(multi);
        thisData.setInternetPwOfMonth(lastData.getInternetPwOfMonth() + multi);
        thisData.setInternetPwOfYear(lastData.getInternetPwOfYear() + multi);
        powerIndicatorService.update(thisData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTotalPwAndHour(double totalPower, double hour, int plantCode, String dateStr, String yesterdayStr) {
        PowerIndicator thisData = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        PowerIndicator lastData = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
        if (thisData == null) {
            thisData = new PowerIndicator();
            BeanUtils.copyProperties(lastData, thisData);
            int firstDay = DateUtil.isFirstDay(dateStr);
            if (firstDay == 1) {
                //月的第一天
                thisData.setPowerOfDay(totalPower);
                thisData.setPowerOfMonth(totalPower);
                thisData.setPowerOfYear(thisData.getPowerOfYear() + totalPower);
            } else if (firstDay == 0) {
                thisData.setPowerOfDay(totalPower);
                thisData.setPowerOfMonth(totalPower);
                thisData.setPowerOfYear(totalPower);
            } else {  //其它时间
                thisData.setPowerOfDay(totalPower);
                thisData.setPowerOfMonth(thisData.getPowerOfMonth() + totalPower);
                thisData.setPowerOfYear(thisData.getPowerOfYear() + totalPower);
            }

            //设置日利用小时数
            thisData.setDayUserOfHour(hour);
            thisData.setCreateDate(new Date());
            thisData.setPlantCode(plantCode);
            powerIndicatorService.insert(thisData);
            return;
        }

        thisData.setDayUserOfHour(hour);
        thisData.setPowerOfDay(totalPower);
        thisData.setPowerOfMonth(lastData.getPowerOfMonth() + totalPower);
        thisData.setPowerOfYear(lastData.getPowerOfYear() + totalPower);
        powerIndicatorService.update(thisData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDailyMaxLoad(int plantCode, String dateStr, double dailyMaxLoad, long dailyMaxTime, double dailyMaxRate) {
        EquiRunningAnalysic equiRunningAnalysic = equiRunningAnalysicService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        if (equiRunningAnalysic == null) {
            equiRunningAnalysic = new EquiRunningAnalysic();
            equiRunningAnalysic.setMaxLoad(dailyMaxLoad);
            equiRunningAnalysic.setMaxLoadTime(String.valueOf(dailyMaxTime));
            equiRunningAnalysic.setMaxLoadRate(String.valueOf(dailyMaxRate));
            equiRunningAnalysic.setCreateDate(new Date());
            equiRunningAnalysic.setPlantCode(plantCode);
            equiRunningAnalysicService.insert(equiRunningAnalysic);
            return;
        }
        equiRunningAnalysic.setMaxLoad(dailyMaxLoad);
        equiRunningAnalysic.setMaxLoadTime(String.valueOf(dailyMaxTime));
        equiRunningAnalysic.setMaxLoadRate(String.valueOf(dailyMaxRate));
        equiRunningAnalysic.setCreateDate(new Date());
        equiRunningAnalysicService.update(equiRunningAnalysic);

    }

    private SolarEnergy querySolarEnergy(int plantCode, String dateStr) {
        return solarEnergyService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
    }


    /**
     * 85B-3585相关指标计算
     *
     * @param val_3585
     * @param dateStr
     * @param plantCode
     * @param yesterdayStr
     */
    @Override
    @Transactional
    public void saveVal_3585(double val_3585, String dateStr, int plantCode, String yesterdayStr) {
        EnergyComsume energyComsume = energyComsumeService.queryDataByTimeAndPlantCode(dateStr, plantCode);
        EnergyComsume energyComsume1 = energyComsumeService.queryDataByTimeAndPlantCode(yesterdayStr, plantCode);

        if (energyComsume == null) {
            EnergyComsume newEnergyComsume = new EnergyComsume();
            newEnergyComsume.setCreateDate(new Date());
            newEnergyComsume.setPlantCode(plantCode);
            BeanUtils.copyProperties(energyComsume1, newEnergyComsume);
            int firstDay = DateUtil.isFirstDay(dateStr);
            //月的第一天
            if (firstDay == 1) {
                newEnergyComsume.setIn85B3585usePwOfDay(val_3585);
                newEnergyComsume.setIn85B3585usePwOfMonth(val_3585);
                newEnergyComsume.setIn85B3585usePwOfYear(newEnergyComsume.getIn85B3585usePwOfYear() + val_3585);

            } else if (firstDay == 0) {
                //年的第一天
                newEnergyComsume.setIn85B3585usePwOfDay(val_3585);
                newEnergyComsume.setIn85B3585usePwOfMonth(val_3585);
                newEnergyComsume.setIn85B3585usePwOfYear(val_3585);

            } else {
                //既不是月的第一天也不是年的第一天
                newEnergyComsume.setIn85B3585usePwOfDay(val_3585);
                newEnergyComsume.setIn85B3585usePwOfMonth(newEnergyComsume.getIn85B3585usePwOfMonth() + val_3585);
                newEnergyComsume.setIn85B3585usePwOfYear(newEnergyComsume.getIn85B3585usePwOfYear() + val_3585);
            }
            energyComsumeService.addData(newEnergyComsume);
            return;
        }
        //当天以内的累加计算
        energyComsume.setIn85B3585usePwOfDay(energyComsume1.getIn85B3585usePwOfDay() + val_3585);
        energyComsume.setIn85B3585usePwOfMonth(energyComsume1.getIn85B3585usePwOfMonth() + val_3585);
        energyComsume.setIn85B3585usePwOfYear(energyComsume1.getIn85B3585usePwOfYear() + val_3585);
        energyComsumeService.updateData(energyComsume);
    }

    /**
     * 3590相关指标计算
     *
     * @param val_3590
     * @param dateStr
     * @param plantCode
     * @param yesterdayStr
     */
    @Override
    @Transactional
    public void saveVal_3590(double val_3590, String dateStr, int plantCode, String yesterdayStr) {
        EnergyComsume energyComsume = energyComsumeService.queryDataByTimeAndPlantCode(dateStr, plantCode);
        EnergyComsume energyComsume1 = energyComsumeService.queryDataByTimeAndPlantCode(yesterdayStr, plantCode);

        if (energyComsume == null) {
            EnergyComsume newEnergyComsume = new EnergyComsume();
            newEnergyComsume.setCreateDate(new Date());
            newEnergyComsume.setPlantCode(plantCode);
            BeanUtils.copyProperties(energyComsume1, newEnergyComsume);
            int firstDay = DateUtil.isFirstDay(dateStr);
            //月的第一天
            if (firstDay == 1) {
                newEnergyComsume.setOut3590UsePwOfDay(val_3590);
                newEnergyComsume.setOut3590UsePwOfMonth(val_3590);
                newEnergyComsume.setOut3590UsePwOfYear(newEnergyComsume.getOut3590UsePwOfYear() + val_3590);

            } else if (firstDay == 0) {
                //年的第一天
                newEnergyComsume.setOut3590UsePwOfDay(val_3590);
                newEnergyComsume.setOut3590UsePwOfMonth(val_3590);
                newEnergyComsume.setOut3590UsePwOfYear(val_3590);

            } else {
                //既不是月的第一天也不是年的第一天
                newEnergyComsume.setOut3590UsePwOfDay(val_3590);
                newEnergyComsume.setOut3590UsePwOfMonth(newEnergyComsume.getOut3590UsePwOfMonth() + val_3590);
                newEnergyComsume.setOut3590UsePwOfYear(newEnergyComsume.getOut3590UsePwOfYear() + val_3590);
            }
            energyComsumeService.addData(newEnergyComsume);
            return;
        }

        //当天以内的累加计算
        energyComsume.setOut3590UsePwOfDay(energyComsume1.getOut3590UsePwOfDay() + val_3590);
        energyComsume.setOut3590UsePwOfMonth(energyComsume1.getOut3590UsePwOfMonth() + val_3590);
        energyComsume.setOut3590UsePwOfYear(energyComsume1.getOut3590UsePwOfYear() + val_3590);
        energyComsumeService.updateData(energyComsume);
    }

    /**
     * 95B_3595相关指标计算
     *
     * @param val_3595
     * @param dateStr
     * @param plantCode
     * @param yesterdayStr
     */
    @Override
    @Transactional
    public void saveVal_3595(double val_3595, String dateStr, int plantCode, String yesterdayStr) {
        EnergyComsume energyComsume = energyComsumeService.queryDataByTimeAndPlantCode(dateStr, plantCode);
        EnergyComsume energyComsume1 = energyComsumeService.queryDataByTimeAndPlantCode(yesterdayStr, plantCode);

        if (energyComsume == null) {
            EnergyComsume newEnergyComsume = new EnergyComsume();
            newEnergyComsume.setCreateDate(new Date());
            newEnergyComsume.setPlantCode(plantCode);
            BeanUtils.copyProperties(energyComsume1, newEnergyComsume);
            int firstDay = DateUtil.isFirstDay(dateStr);

            //月的第一天
            if (firstDay == 1) {
                newEnergyComsume.setIn95B3595usePwOfDay(val_3595);
                newEnergyComsume.setIn95B3595usePwOfMonth(val_3595);
                newEnergyComsume.setIn95B3595usePwOfYear(newEnergyComsume.getIn95B3595usePwOfYear() + val_3595);

            } else if (firstDay == 0) {
                //年的第一天
                newEnergyComsume.setIn95B3595usePwOfDay(val_3595);
                newEnergyComsume.setIn95B3595usePwOfMonth(val_3595);
                newEnergyComsume.setIn95B3595usePwOfYear(val_3595);

            } else {
                //既不是月的第一天也不是年的第一天
                newEnergyComsume.setIn95B3595usePwOfDay(val_3595);
                newEnergyComsume.setIn95B3595usePwOfMonth(newEnergyComsume.getIn95B3595usePwOfMonth() + val_3595);
                newEnergyComsume.setIn95B3595usePwOfYear(newEnergyComsume.getIn95B3595usePwOfYear() + val_3595);
            }
            energyComsumeService.addData(newEnergyComsume);
            return;
        }
        //当天以内的累加计算
        energyComsume.setIn95B3595usePwOfDay(energyComsume1.getIn95B3595usePwOfDay() + val_3595);
        energyComsume.setIn95B3595usePwOfMonth(energyComsume1.getIn95B3595usePwOfMonth() + val_3595);
        energyComsume.setIn95B3595usePwOfYear(energyComsume1.getIn95B3595usePwOfYear() + val_3595);
        energyComsumeService.updateData(energyComsume);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSunShineHours(int plantCode, String dateStr, String yesterdayStr, double sunShineHours) {
        SolarEnergy solarEnergy = querySolarEnergy(plantCode, dateStr);
        SolarEnergy last = querySolarEnergy(plantCode, yesterdayStr);
        if (solarEnergy == null) {
            solarEnergy = new SolarEnergy();
            BeanUtils.copyProperties(last, solarEnergy);
            int firstDay = DateUtil.isFirstDay(dateStr);
            //月的第一天
            if (firstDay == 1) {
                solarEnergy.setHourOfDay(sunShineHours);
                solarEnergy.setHourOfMonth(sunShineHours);
                solarEnergy.setHourOfYear(solarEnergy.getHourOfYear() + sunShineHours);

            } else if (firstDay == 0) {
                //年的第一天
                solarEnergy.setHourOfDay(sunShineHours);
                solarEnergy.setHourOfMonth(sunShineHours);
                solarEnergy.setHourOfYear(sunShineHours);
            } else {
                //既不是月的第一天也不是年的第一天
                solarEnergy.setHourOfDay(sunShineHours);
                solarEnergy.setHourOfMonth(solarEnergy.getHourOfMonth() + sunShineHours);
                solarEnergy.setHourOfYear(solarEnergy.getHourOfYear() + sunShineHours);
            }

            solarEnergy.setCreateDate(new Date());
            solarEnergy.setPlantCode(plantCode);
            solarEnergyService.insert(solarEnergy);
            return;
        }

        solarEnergy.setHourOfDay(sunShineHours);
        solarEnergy.setHourOfMonth(last.getHourOfMonth() + sunShineHours);
        solarEnergy.setHourOfYear(last.getHourOfYear() + sunShineHours);
        solarEnergyService.update(solarEnergy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReverserPower(int plantCode, String dateStr, String yesterdayStr, double reversePower) {
        PowerIndicator powerIndicator = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        PowerIndicator last = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
        if (powerIndicator == null) {
            powerIndicator = new PowerIndicator();
            BeanUtils.copyProperties(last, powerIndicator);
            int firstDay = DateUtil.isFirstDay(dateStr);
            //月的第一天
            if (firstDay == 1) {
                powerIndicator.setBackPwOfDay(reversePower);
                powerIndicator.setBackPwOfMonth(reversePower);
                powerIndicator.setBackPwOfYear(powerIndicator.getBackPwOfYear() + reversePower);
            } else if (firstDay == 0) {
                //年的第一天
                powerIndicator.setBackPwOfDay(reversePower);
                powerIndicator.setBackPwOfMonth(reversePower);
                powerIndicator.setBackPwOfYear(reversePower);
            } else {
                //既不是月的第一天也不是年的第一天
                powerIndicator.setBackPwOfDay(reversePower);
                powerIndicator.setBackPwOfMonth(powerIndicator.getBackPwOfMonth() + reversePower);
                powerIndicator.setBackPwOfYear(powerIndicator.getBackPwOfYear() + reversePower);
            }

            powerIndicator.setCreateDate(new Date());
            powerIndicator.setPlantCode(plantCode);
            powerIndicatorService.insert(powerIndicator);
            return;
        }

        powerIndicator.setBackPwOfDay(reversePower);
        powerIndicator.setBackPwOfMonth(last.getBackPwOfMonth() + reversePower);
        powerIndicator.setBackPwOfYear(last.getBackPwOfYear() + reversePower);
        powerIndicatorService.update(powerIndicator);

        //计算购网电量(日外来电量+返送电量)
        EnergyComsume energyComsume = energyComsumeService.queryDataByTimeAndPlantCode(dateStr, plantCode);
        EnergyComsume lastData = energyComsumeService.queryDataByTimeAndPlantCode(yesterdayStr, plantCode);

        //日外来电量（3590）
        double val_3590 = 0D;
        if (energyComsume == null) {
            energyComsume = new EnergyComsume();
            BeanUtils.copyProperties(lastData, energyComsume);
            int firstDay = DateUtil.isFirstDay(dateStr);
            //月的第一天
            if (firstDay == 1) {
                energyComsume.setPurchasePwOfDay(reversePower + powerIndicator.getBackPwOfDay());
                energyComsume.setPurchasePwOfMonth(reversePower + powerIndicator.getBackPwOfDay());
                energyComsume.setPurchasePwOfYear(powerIndicator.getBackPwOfYear() + reversePower + powerIndicator.getBackPwOfDay());
            } else if (firstDay == 0) {
                //年的第一天
                energyComsume.setPurchasePwOfDay(reversePower + powerIndicator.getBackPwOfDay());
                energyComsume.setPurchasePwOfMonth(reversePower + powerIndicator.getBackPwOfDay());
                energyComsume.setPurchasePwOfYear(reversePower + powerIndicator.getBackPwOfDay());
            } else {
                //既不是月的第一天也不是年的第一天
                energyComsume.setPurchasePwOfDay(energyComsume.getPurchasePwOfDay() + reversePower + powerIndicator.getBackPwOfDay());
                energyComsume.setPurchasePwOfMonth(energyComsume.getPurchasePwOfMonth() + powerIndicator.getBackPwOfMonth() + reversePower);
                energyComsume.setPurchasePwOfYear(energyComsume.getPurchasePwOfYear() + powerIndicator.getBackPwOfDay() + reversePower);
            }

            energyComsume.setCreateDate(new Date());
            energyComsume.setPlantCode(plantCode);
            energyComsumeService.addData(energyComsume);
            return;
        }

        energyComsume.setPurchasePwOfDay(lastData.getPurchasePwOfDay() + reversePower + powerIndicator.getBackPwOfDay());
        energyComsume.setPurchasePwOfMonth(lastData.getPurchasePwOfMonth() + powerIndicator.getBackPwOfMonth() + reversePower);
        energyComsume.setPurchasePwOfYear(lastData.getPurchasePwOfYear() + powerIndicator.getBackPwOfDay() + reversePower);
        energyComsumeService.updateData(energyComsume);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIrradtionVal(int plantCode, String dateStr, String yesterdayStr, double irradtionVal) {
        SolarEnergy solarEnergy = querySolarEnergy(plantCode, dateStr);
        SolarEnergy lastData = querySolarEnergy(plantCode, yesterdayStr);
        if (solarEnergy == null) {
            solarEnergy = new SolarEnergy();
            BeanUtils.copyProperties(lastData, solarEnergy);
            int firstDay = DateUtil.isFirstDay(dateStr);
            if (firstDay == 1) {
                //月的第一天
                solarEnergy.setHeOfDay(irradtionVal);
                solarEnergy.setHeOfMonth(irradtionVal);
                solarEnergy.setHeOfYear(solarEnergy.getHeOfYear() + irradtionVal);
            } else if (firstDay == 0) {
                //年的第一天
                solarEnergy.setHeOfDay(irradtionVal);
                solarEnergy.setHeOfMonth(irradtionVal);
                solarEnergy.setHeOfYear(irradtionVal);
            } else {
                //既不是月的第一天也不是年的第一天
                solarEnergy.setHeOfDay(irradtionVal);
                solarEnergy.setHeOfMonth(solarEnergy.getHeOfMonth() + irradtionVal);
                solarEnergy.setHeOfYear(solarEnergy.getHeOfYear() + irradtionVal);
            }

            solarEnergy.setPlantCode(plantCode);
            solarEnergy.setCreateDate(new Date());
            solarEnergyService.insert(solarEnergy);
            return;
        }

        solarEnergy.setHeOfDay(irradtionVal);
        solarEnergy.setHeOfMonth(lastData.getHeOfMonth() + irradtionVal);
        solarEnergy.setHeOfYear(lastData.getHeOfYear() + irradtionVal);
        solarEnergy.setCreateDate(new Date());
        solarEnergyService.update(solarEnergy);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHours(int plantCode, String dateStr, String yesterdayStr, double hours) {
        EquiRunningAnalysic thisData = equiRunningAnalysicService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        EquiRunningAnalysic lastData = equiRunningAnalysicService.queryByPlantCodeAndCreateDate(plantCode, yesterdayStr);
        if (thisData == null) {
            thisData = lastData;
            int firstDay = DateUtil.isFirstDay(dateStr);
            if (firstDay == 1) {
                //月的第一天
                thisData.setRuningHourOfDay(hours);
                thisData.setRuningHourOfMonth(hours);
                thisData.setRuningHourOfYear(thisData.getRuningHourOfYear() + hours);
            } else if (firstDay == 0) {
                //年的第一天
                thisData.setRuningHourOfDay(hours);
                thisData.setRuningHourOfMonth(hours);
                thisData.setRuningHourOfYear(hours);
            } else {
                //既不是月的第一天也不是年的第一天
                thisData.setRuningHourOfDay(hours);
                thisData.setRuningHourOfMonth(hours + thisData.getRuningHourOfMonth());
                thisData.setRuningHourOfYear(thisData.getRuningHourOfYear() + hours);
            }

            thisData.setPlantCode(plantCode);
            thisData.setCreateDate(new Date());
            equiRunningAnalysicService.insert(thisData);
            return;
        }

        thisData.setRuningHourOfDay(hours);
        thisData.setRuningHourOfMonth(lastData.getRuningHourOfMonth() + hours);
        thisData.setRuningHourOfYear(lastData.getRuningHourOfYear() + hours);
        equiRunningAnalysicService.update(thisData);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFactoryPWAndRate(int plantCode, String dateStr, String yesterdayStr) {
        double factoryPWOfDay = 0.0;
        double factoryPWOfMonth = 0.0;
        double factoryPwOfYear = 0.0;

        //综合厂用电量（发电量-上网电量+购网电量）
        PowerIndicator powerIndicator = powerIndicatorService.queryByPlantCodeAndCreateDate(plantCode, dateStr);
        EnergyComsume energyComsume = energyComsumeService.queryDataByTimeAndPlantCode(dateStr, plantCode);
        if (powerIndicator != null && energyComsume != null) {
            factoryPWOfDay = powerIndicator.getPowerOfDay() - powerIndicator.getInternetPwOfDay() + energyComsume.getPurchasePwOfDay();
            factoryPWOfMonth = powerIndicator.getPowerOfMonth() - powerIndicator.getInternetPwOfMonth() + energyComsume.getPurchasePwOfMonth();
            factoryPwOfYear = powerIndicator.getPowerOfYear() - powerIndicator.getInternetPwOfYear() + energyComsume.getPurchasePwOfYear();
            energyComsume.setFactoryPw(factoryPWOfDay);
            energyComsume.setFactoryPwOfMonth(factoryPWOfMonth);
            energyComsume.setFactoryPwOfYear(factoryPwOfYear);

            //综合厂用电率(综合厂用电量/发电量*100)
            energyComsume.setFactoryRateOfDay(BasicCalaUtils.multi(BasicCalaUtils.div(factoryPWOfDay, powerIndicator.getPowerOfDay()), 100));
            energyComsume.setFactoryRateOfDay(BasicCalaUtils.multi(BasicCalaUtils.div(factoryPWOfMonth, powerIndicator.getPowerOfMonth()), 100));
            energyComsume.setFactoryRateOfDay(BasicCalaUtils.multi(BasicCalaUtils.div(factoryPwOfYear, powerIndicator.getPowerOfYear()), 100));
            energyComsumeService.updateData(energyComsume);
        }

    }

}
