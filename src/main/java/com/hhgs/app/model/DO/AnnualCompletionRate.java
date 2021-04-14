package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 年度完成率
 */
public class AnnualCompletionRate {

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
     * 年实际运行天数
     */
    private double runningDays;

    /**
     * 年计划发电量
     */
    private double powerOfPlan;

    /**
     * 年计划完成率
     */
    private double rateOfPlan;

    /**
     * 年计划进度比
     */
    private double progressRate;

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

    public double getRunningDays() {
        return runningDays;
    }

    public void setRunningDays(double runningDays) {
        this.runningDays = runningDays;
    }

    public double getPowerOfPlan() {
        return powerOfPlan;
    }

    public void setPowerOfPlan(double powerOfPlan) {
        this.powerOfPlan = powerOfPlan;
    }

    public double getRateOfPlan() {
        return rateOfPlan;
    }

    public void setRateOfPlan(double rateOfPlan) {
        this.rateOfPlan = rateOfPlan;
    }

    public double getProgressRate() {
        return progressRate;
    }

    public void setProgressRate(double progressRate) {
        this.progressRate = progressRate;
    }
}
