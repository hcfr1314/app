package com.hhgs.app.model.DO.wind;

import java.util.Date;

/**
 * 设备运行水平可靠性WEBAPP_WIND_EQUIPMENT
 */
public class WindEquimentRunning {

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
    private Date createTime;

    /**
     * 日最大负荷
     */
    private double dailyMaxLoad;

    /**
     * 最大负荷时间点
     */
    private double dailyMaxLoadTime;

    /**
     * 日负荷率(平均负荷)
     */
    private double dailyLoadRate;

    /**
     * 日运行小时
     */
    private double dailyRunningHour;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getDailyMaxLoad() {
        return dailyMaxLoad;
    }

    public void setDailyMaxLoad(double dailyMaxLoad) {
        this.dailyMaxLoad = dailyMaxLoad;
    }

    public double getDailyMaxLoadTime() {
        return dailyMaxLoadTime;
    }

    public void setDailyMaxLoadTime(double dailyMaxLoadTime) {
        this.dailyMaxLoadTime = dailyMaxLoadTime;
    }

    public double getDailyLoadRate() {
        return dailyLoadRate;
    }

    public void setDailyLoadRate(double dailyLoadRate) {
        this.dailyLoadRate = dailyLoadRate;
    }

    public double getDailyRunningHour() {
        return dailyRunningHour;
    }

    public void setDailyRunningHour(double dailyRunningHour) {
        this.dailyRunningHour = dailyRunningHour;
    }
}
