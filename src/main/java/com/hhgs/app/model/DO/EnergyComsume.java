package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 能耗指标(WEBAPP_ENERGY_CONSUMPTION)
 */
public class EnergyComsume {

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
     * 日累计外来电源用3590电量
     */
    private double out3590UsePwOfDay;

    /**
     * 月累计外来电源用3590电量
     */
    private double out3590UsePwOfMonth;

    /**
     * 年累计外来电源用3590电量
     */
    private double out3590UsePwOfYear;


    /**
     * 日累计站用85B-3585电量
     */
    private double in85B3585usePwOfDay;

    /**
     * 月累计站用85B-3585电量
     */
    private double in85B3585usePwOfMonth;

    /**
     * 年累计站用85B-3585电量
     */
    private double in85B3585usePwOfYear;


    /**
     * 日累计站用95B-3595电量
     */
    private double in95B3595usePwOfDay;

    /**
     * 月累计站用95B-3595电量
     */
    private double in95B3595usePwOfMonth;

    /**
     * 年累计站用95B-3595电量
     */
    private double in95B3595usePwOfYear;

    /**
     * 购网电量日累计
     */
    private double purchasePwOfDay;

    /**
     * 购网电量日累计
     */
    private double purchasePwOfMonth;

    /**
     * 购网电量日累计
     */
    private double purchasePwOfYear;

    /**
     * 综合厂用电量日累计
     */
    private double factoryPw;

    /**
     * 综合厂用电量
     */
    private double factoryPwOfMonth;

    /**
     * 综合厂用电量
     */
    private double factoryPwOfYear;

    /**
     * 综合厂用电率日累计
     */
    private double factoryRateOfDay;

    /**
     * 综合厂用电率日累计
     */
    private double factoryRateOfMonth;

    /**
     * 综合厂用电率日累计
     */
    private double factoryRateOfYear;

    public double getFactoryPwOfMonth() {
        return factoryPwOfMonth;
    }

    public void setFactoryPwOfMonth(double factoryPwOfMonth) {
        this.factoryPwOfMonth = factoryPwOfMonth;
    }

    public double getFactoryPwOfYear() {
        return factoryPwOfYear;
    }

    public void setFactoryPwOfYear(double factoryPwOfYear) {
        this.factoryPwOfYear = factoryPwOfYear;
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

    public double getOut3590UsePwOfDay() {
        return out3590UsePwOfDay;
    }

    public void setOut3590UsePwOfDay(double out3590UsePwOfDay) {
        this.out3590UsePwOfDay = out3590UsePwOfDay;
    }

    public double getOut3590UsePwOfMonth() {
        return out3590UsePwOfMonth;
    }

    public void setOut3590UsePwOfMonth(double out3590UsePwOfMonth) {
        this.out3590UsePwOfMonth = out3590UsePwOfMonth;
    }

    public double getOut3590UsePwOfYear() {
        return out3590UsePwOfYear;
    }

    public void setOut3590UsePwOfYear(double out3590UsePwOfYear) {
        this.out3590UsePwOfYear = out3590UsePwOfYear;
    }

    public double getIn95B3595usePwOfDay() {
        return in95B3595usePwOfDay;
    }

    public void setIn95B3595usePwOfDay(double in95B3595usePwOfDay) {
        this.in95B3595usePwOfDay = in95B3595usePwOfDay;
    }

    public double getIn95B3595usePwOfMonth() {
        return in95B3595usePwOfMonth;
    }

    public void setIn95B3595usePwOfMonth(double in95B3595usePwOfMonth) {
        this.in95B3595usePwOfMonth = in95B3595usePwOfMonth;
    }

    public double getIn95B3595usePwOfYear() {
        return in95B3595usePwOfYear;
    }

    public void setIn95B3595usePwOfYear(double in95B3595usePwOfYear) {
        this.in95B3595usePwOfYear = in95B3595usePwOfYear;
    }

    public double getPurchasePwOfDay() {
        return purchasePwOfDay;
    }

    public void setPurchasePwOfDay(double purchasePwOfDay) {
        this.purchasePwOfDay = purchasePwOfDay;
    }

    public double getPurchasePwOfMonth() {
        return purchasePwOfMonth;
    }

    public void setPurchasePwOfMonth(double purchasePwOfMonth) {
        this.purchasePwOfMonth = purchasePwOfMonth;
    }

    public double getPurchasePwOfYear() {
        return purchasePwOfYear;
    }

    public void setPurchasePwOfYear(double purchasePwOfYear) {
        this.purchasePwOfYear = purchasePwOfYear;
    }

    public double getFactoryPw() {
        return factoryPw;
    }

    public void setFactoryPw(double factoryPw) {
        this.factoryPw = factoryPw;
    }

    public double getFactoryRateOfDay() {
        return factoryRateOfDay;
    }

    public void setFactoryRateOfDay(double factoryRateOfDay) {
        this.factoryRateOfDay = factoryRateOfDay;
    }

    public double getFactoryRateOfMonth() {
        return factoryRateOfMonth;
    }

    public void setFactoryRateOfMonth(double factoryRateOfMonth) {
        this.factoryRateOfMonth = factoryRateOfMonth;
    }

    public double getFactoryRateOfYear() {
        return factoryRateOfYear;
    }

    public void setFactoryRateOfYear(double factoryRateOfYear) {
        this.factoryRateOfYear = factoryRateOfYear;
    }

    public double getIn85B3585usePwOfDay() {
        return in85B3585usePwOfDay;
    }

    public void setIn85B3585usePwOfDay(double in85B3585usePwOfDay) {
        this.in85B3585usePwOfDay = in85B3585usePwOfDay;
    }

    public double getIn85B3585usePwOfMonth() {
        return in85B3585usePwOfMonth;
    }

    public void setIn85B3585usePwOfMonth(double in85B3585usePwOfMonth) {
        this.in85B3585usePwOfMonth = in85B3585usePwOfMonth;
    }

    public double getIn85B3585usePwOfYear() {
        return in85B3585usePwOfYear;
    }

    public void setIn85B3585usePwOfYear(double in85B3585usePwOfYear) {
        this.in85B3585usePwOfYear = in85B3585usePwOfYear;
    }
}
