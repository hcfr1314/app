package com.hhgs.app.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 太阳能资源指标实体类，对应表明WEBAPP_SOLAR_ENERGY_RESOURCE_INDEX
 */
public class SolarEnergy {

    /**
     * 主键
     */
    private int id;

    /**
     * 场站编码
     */
    private int plantCode;

    /**
     * 当日辐照量
     */
    private double heOfDay;

    /**
     * 月累计辐照量
     */
    private double heOfMonth;

    /**
     * 年累计辐照量
     */
    private double heOfYear;

    /**
     * 当日日照小时
     */

    private double hourOfDay;

    /**
     * 月累计日照小时
     */
    private double hourOfMonth;

    /**
     * 年累计日用小时
     */
    private double hourOfYear;

    /**
     * 平均风速
     */
    private double avgWindSpeed;

    /**
     * 平均温度
     */
    private double avgTemperature;

    //辐照度
    private double irradiance;

    /**
     * 创建日期
     */
    private Date createDate;

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

    public double getHeOfDay() {
        return heOfDay;
    }

    public void setHeOfDay(double heOfDay) {
        this.heOfDay = heOfDay;
    }

    public double getHeOfMonth() {
        return heOfMonth;
    }

    public void setHeOfMonth(double heOfMonth) {
        this.heOfMonth = heOfMonth;
    }

    public double getHeOfYear() {
        return heOfYear;
    }

    public void setHeOfYear(double heOfYear) {
        this.heOfYear = heOfYear;
    }

    public double getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(double hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public double getHourOfMonth() {
        return hourOfMonth;
    }

    public void setHourOfMonth(double hourOfMonth) {
        this.hourOfMonth = hourOfMonth;
    }

    public double getHourOfYear() {
        return hourOfYear;
    }

    public void setHourOfYear(double hourOfYear) {
        this.hourOfYear = hourOfYear;
    }

    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getIrradiance() {
        return irradiance;
    }

    public void setIrradiance(double irradiance) {
        this.irradiance = irradiance;
    }
}
