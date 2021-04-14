package com.hhgs.app.model.DO.wind;

import java.util.Date;

/**
 * 能耗指标WEBAPP_WIND_ENERGY_CONSUMPTION
 */
public class WindEnergyConsume {

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
     * 外来电源用电量日累计
     */
    private double outPwDay;

    /**
     * 外来电源用电量月累计
     */
    private double outPwMonth;

    /**
     * 外来电源用电量年累计
     */
    private double outPwYear;

    /**
     * 站用变电量日累计
     */
    private double stationUsePwDay;

    /**
     * 站用变电量月累计
     */
    private double stationUsePwMonth;

    /**
     * 站用变电量年累计
     */
    private double stationUsePwYear;

    /**
     * 购网电量
     */
    private double purchseNetPw;

    /**
     * 综合厂用电量
     */
    private double pwPlantAll;

    /**
     * 综合厂用电率
     */
    private double ratePlantAll;

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

    public double getOutPwDay() {
        return outPwDay;
    }

    public void setOutPwDay(double outPwDay) {
        this.outPwDay = outPwDay;
    }

    public double getOutPwMonth() {
        return outPwMonth;
    }

    public void setOutPwMonth(double outPwMonth) {
        this.outPwMonth = outPwMonth;
    }

    public double getOutPwYear() {
        return outPwYear;
    }

    public void setOutPwYear(double outPwYear) {
        this.outPwYear = outPwYear;
    }

    public double getStationUsePwDay() {
        return stationUsePwDay;
    }

    public void setStationUsePwDay(double stationUsePwDay) {
        this.stationUsePwDay = stationUsePwDay;
    }

    public double getStationUsePwMonth() {
        return stationUsePwMonth;
    }

    public void setStationUsePwMonth(double stationUsePwMonth) {
        this.stationUsePwMonth = stationUsePwMonth;
    }

    public double getStationUsePwYear() {
        return stationUsePwYear;
    }

    public void setStationUsePwYear(double stationUsePwYear) {
        this.stationUsePwYear = stationUsePwYear;
    }

    public double getPurchseNetPw() {
        return purchseNetPw;
    }

    public void setPurchseNetPw(double purchseNetPw) {
        this.purchseNetPw = purchseNetPw;
    }

    public double getPwPlantAll() {
        return pwPlantAll;
    }

    public void setPwPlantAll(double pwPlantAll) {
        this.pwPlantAll = pwPlantAll;
    }

    public double getRatePlantAll() {
        return ratePlantAll;
    }

    public void setRatePlantAll(double ratePlantAll) {
        this.ratePlantAll = ratePlantAll;
    }
}
