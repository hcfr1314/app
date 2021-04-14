package com.hhgs.app.model.DO.wind;

import java.util.Date;

/**
 * 风资源指标（WEBAPP_WIND_RESOURCE）
 */
public class WindResource {

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
     * 最小风速
     */
    private double minWindSpeed;

    /**
     * 最大风速
     */
    private double maxWindSpeed;

    /**
     * 平均风速
     */
    private double avgWindSpeed;

    /**
     * 有效风时速数日
     */
    private double effectiveWindDay;

    /**
     * 有效风时速数月
     */
    private double effectiveWindMonth;

    /**
     * 有效风时速数年
     */
    private double effectiveWindYear;

    /**
     * 有效风时数平均风速日累计
     */
    private double effectiveWindAvgDay;

    /**
     * 有效风时数平均风速月累计
     */
    private double getEffectiveWindAvgMonth;

    /**
     * 有效风时数平均风速年累计
     */
    private double getEffectiveWindAvgYear;

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

    public double getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(double minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public double getEffectiveWindDay() {
        return effectiveWindDay;
    }

    public void setEffectiveWindDay(double effectiveWindDay) {
        this.effectiveWindDay = effectiveWindDay;
    }

    public double getEffectiveWindMonth() {
        return effectiveWindMonth;
    }

    public void setEffectiveWindMonth(double effectiveWindMonth) {
        this.effectiveWindMonth = effectiveWindMonth;
    }

    public double getEffectiveWindYear() {
        return effectiveWindYear;
    }

    public void setEffectiveWindYear(double effectiveWindYear) {
        this.effectiveWindYear = effectiveWindYear;
    }

    public double getEffectiveWindAvgDay() {
        return effectiveWindAvgDay;
    }

    public void setEffectiveWindAvgDay(double effectiveWindAvgDay) {
        this.effectiveWindAvgDay = effectiveWindAvgDay;
    }

    public double getGetEffectiveWindAvgMonth() {
        return getEffectiveWindAvgMonth;
    }

    public void setGetEffectiveWindAvgMonth(double getEffectiveWindAvgMonth) {
        this.getEffectiveWindAvgMonth = getEffectiveWindAvgMonth;
    }

    public double getGetEffectiveWindAvgYear() {
        return getEffectiveWindAvgYear;
    }

    public void setGetEffectiveWindAvgYear(double getEffectiveWindAvgYear) {
        this.getEffectiveWindAvgYear = getEffectiveWindAvgYear;
    }
}
