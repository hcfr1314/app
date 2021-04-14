package com.hhgs.app.service;

import java.util.Date;

public interface TaskService {

    /**
     * 保存或更新平均温度
     * @param avgTem
     * @param date
     * @param plantCode
     * @param yesterdayStr
     */
    void saveAvgTmp(double avgTem, String date, int plantCode, String yesterdayStr);


    void saveVal_3585(double val_3585, String dateStr, int plantCode, String yesterdayStr);


    /**
     * 保存或更新平均风速
     * @param avgSpeed
     * @param dateStr
     * @param plantCode
     * @param yesterdayStr
     */
    void saveAvgSpeed(double avgSpeed, String dateStr, int plantCode, String yesterdayStr);


    /**
     * 保存或更新日累计上网电量，月累计上网电量，年累计上网电量
     * @param multi
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     */
    void saveInternetPw(double multi, int plantCode, String dateStr, String yesterdayStr);

    void saveVal_3590(double val_3590, String dateStr, int plantCode, String yesterdayStr);

    void saveVal_3595(double val_3595, String dateStr, int plantCode, String yesterdayStr);


    /**
     * 保存或更新日累计发电量，月累计发电量，年累计发电量，日利用小时
     * @param totalPower
     * @param hour
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     */
    void saveTotalPwAndHour(double totalPower, double hour, int plantCode, String dateStr, String yesterdayStr);

    /**
     * 新插入一条负或者更新日最大负荷，日最大符合点，日最大负荷率
     * @param plantCode
     * @param dateStr
     * @param dailyMaxLoad
     * @param dailyMaxTime
     * @param dailyMaxRate
     */
    void saveDailyMaxLoad(int plantCode, String dateStr, double dailyMaxLoad, long dailyMaxTime, double dailyMaxRate);


    /**
     * 日照小时日累计，月累计，年累计计算并入库
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     * @param sunShineHours
     */
    void saveSunShineHours(int plantCode, String dateStr, String yesterdayStr, double sunShineHours);

    /**
     * 日返送电量，月返送电量，年累计返送电量,以及计算日购网电量（）
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     * @param reversePower
     */
    void saveReverserPower(int plantCode, String dateStr, String yesterdayStr, double reversePower);

    /**
     * 辐照量日累计，月累计。年累计
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     * @param irradtionVal
     */
    void saveIrradtionVal(int plantCode, String dateStr, String yesterdayStr, double irradtionVal);


    /**
     * 日运行小时日累计，月累计，年累计
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     * @param hours
     */
    void saveHours(int plantCode, String dateStr, String yesterdayStr, double hours);

    /**
     * 计算综合厂用电量和综合厂用电率
     * @param plantCode
     * @param dateStr
     * @param yesterdayStr
     */
    void saveFactoryPWAndRate(int plantCode, String dateStr, String yesterdayStr);

}
