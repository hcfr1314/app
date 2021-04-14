package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 设备运行可行性分析
 */
public class EquiRunningAnalysic {

    /**
     * 主键
     */
    private int id;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 日最大负荷
     */
    private double maxLoad;

    /**
     * 日最大负荷时间点
     */
    private String maxLoadTime;

    /**
     * 日最大负荷率
     */
    private String maxLoadRate;

    /**
     * 日运行小时
     */
    private double runingHourOfDay;

    /**
     * 日运行小时月累计
     */
    private double runingHourOfMonth;

    /**
     * 日运行小时年累计
     */
    private double runingHourOfYear;

    //有功功率
    private double activePower;

    public String getMaxLoadRate() {
        return maxLoadRate;
    }

    public void setMaxLoadRate(String maxLoadRate) {
        this.maxLoadRate = maxLoadRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getMaxLoadTime() {
        return maxLoadTime;
    }

    public void setMaxLoadTime(String maxLoadTime) {
        this.maxLoadTime = maxLoadTime;
    }

    public double getRuningHourOfDay() {
        return runingHourOfDay;
    }

    public void setRuningHourOfDay(double runingHourOfDay) {
        this.runingHourOfDay = runingHourOfDay;
    }

    public double getRuningHourOfMonth() {
        return runingHourOfMonth;
    }

    public void setRuningHourOfMonth(double runingHourOfMonth) {
        this.runingHourOfMonth = runingHourOfMonth;
    }

    public double getRuningHourOfYear() {
        return runingHourOfYear;
    }

    public void setRuningHourOfYear(double runingHourOfYear) {
        this.runingHourOfYear = runingHourOfYear;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }
}
