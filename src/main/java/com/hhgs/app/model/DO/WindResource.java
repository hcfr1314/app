package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 风资源指标(WEBAPP_WIND_RESOURCE)
 */
public class WindResource {

    /**
     * 主键id
     */
    private int id;

    /**
     * 最小风速
     */
    private double minSpeed;

    /**
     * 平均风速
     */
    private double avgSpeed;

    /**
     * 最大风速
     */
    private double maxSpeed;

    /**
     * 有效风日累计
     */
    private double effectiveWindOfDay;

    /**
     * 有效风月累计
     */
    private double effectiveWindOfMonth;

    /**
     * 有效风年累计
     */
    private double effectiveWindOfYear;

    /**
     * 有效风时数平均风速日累计
     */
    private double effetWindAvgOfDay;

    /**
     * 有效风时数平均风速月累计
     */
    private double effetWindAvgOfMonth;

    /**
     * 有效风时数平均风速年累计
     */
    private double effetWindAvgOfYear;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 创建时间
     */
    private Date createTieme;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getEffectiveWindOfDay() {
        return effectiveWindOfDay;
    }

    public void setEffectiveWindOfDay(double effectiveWindOfDay) {
        this.effectiveWindOfDay = effectiveWindOfDay;
    }

    public double getEffectiveWindOfMonth() {
        return effectiveWindOfMonth;
    }

    public void setEffectiveWindOfMonth(double effectiveWindOfMonth) {
        this.effectiveWindOfMonth = effectiveWindOfMonth;
    }

    public double getEffectiveWindOfYear() {
        return effectiveWindOfYear;
    }

    public void setEffectiveWindOfYear(double effectiveWindOfYear) {
        this.effectiveWindOfYear = effectiveWindOfYear;
    }

    public double getEffetWindAvgOfDay() {
        return effetWindAvgOfDay;
    }

    public void setEffetWindAvgOfDay(double effetWindAvgOfDay) {
        this.effetWindAvgOfDay = effetWindAvgOfDay;
    }

    public double getEffetWindAvgOfMonth() {
        return effetWindAvgOfMonth;
    }

    public void setEffetWindAvgOfMonth(double effetWindAvgOfMonth) {
        this.effetWindAvgOfMonth = effetWindAvgOfMonth;
    }

    public double getEffetWindAvgOfYear() {
        return effetWindAvgOfYear;
    }

    public void setEffetWindAvgOfYear(double effetWindAvgOfYear) {
        this.effetWindAvgOfYear = effetWindAvgOfYear;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public Date getCreateTieme() {
        return createTieme;
    }

    public void setCreateTieme(Date createTieme) {
        this.createTieme = createTieme;
    }
}
