package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 电量指标实体类(WEBAPP_ELECTRICITY_INDEX)
 */
public class PowerIndicator {

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
     * 日累计发电量
     */
    private double powerOfDay;

    /**
     * 月累计发电量
     */
    private double powerOfMonth;

    /**
     * 年累计发电量
     */
    private double powerOfYear;

    /**
     *日累计上网电量
     */
    private double internetPwOfDay;

    /**
     *月累计上网电量
     */
    private double internetPwOfMonth;

    /**
     *年累计上网电量
     */
    private double internetPwOfYear;

    /**
     *日累计返送电量
     */
    private double backPwOfDay;

    /**
     *月累计返送电量
     */
    private double backPwOfMonth;

    /**
     *年累计返送电量
     */
    private double backPwOfYear;

    /**
     * 日累计限电电量
     */
    private double limitPwOfDay;

    /**
     * 月累计限电电量
     */
    private double limitPwOfMonth;

    /**
     * 年累计限电电量
     */
    private double limitPwOfYear;

    /**
     * 电网限电弃光电率
     */
    private double limitRate;

    /**
     * 日累计故障弃光电量
     */
    private double errorPwOfDay;

    /**
     * 日累计故障弃光电量
     */
    private double errorPwOfMonth;

    /**
     * 日累计故障弃光电量
     */
    private double errorPwOfYear;

    /**
     * 故障弃光电率
     */
    private double errorRate;

    /**
     * 理论发电量
     */
    private double theoryPower;

    /**
     * 日利用小时数
     */
    private double dayUserOfHour;


    public double getDayUserOfHour() {
        return dayUserOfHour;
    }

    public void setDayUserOfHour(double dayUserOfHour) {
        this.dayUserOfHour = dayUserOfHour;
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

    public double getPowerOfDay() {
        return powerOfDay;
    }

    public void setPowerOfDay(double powerOfDay) {
        this.powerOfDay = powerOfDay;
    }

    public double getPowerOfMonth() {
        return powerOfMonth;
    }

    public void setPowerOfMonth(double powerOfMonth) {
        this.powerOfMonth = powerOfMonth;
    }

    public double getPowerOfYear() {
        return powerOfYear;
    }

    public void setPowerOfYear(double powerOfYear) {
        this.powerOfYear = powerOfYear;
    }

    public double getInternetPwOfDay() {
        return internetPwOfDay;
    }

    public void setInternetPwOfDay(double internetPwOfDay) {
        this.internetPwOfDay = internetPwOfDay;
    }

    public double getInternetPwOfMonth() {
        return internetPwOfMonth;
    }

    public void setInternetPwOfMonth(double internetPwOfMonth) {
        this.internetPwOfMonth = internetPwOfMonth;
    }

    public double getInternetPwOfYear() {
        return internetPwOfYear;
    }

    public void setInternetPwOfYear(double internetPwOfYear) {
        this.internetPwOfYear = internetPwOfYear;
    }

    public double getBackPwOfDay() {
        return backPwOfDay;
    }

    public void setBackPwOfDay(double backPwOfDay) {
        this.backPwOfDay = backPwOfDay;
    }

    public double getBackPwOfMonth() {
        return backPwOfMonth;
    }

    public void setBackPwOfMonth(double backPwOfMonth) {
        this.backPwOfMonth = backPwOfMonth;
    }

    public double getBackPwOfYear() {
        return backPwOfYear;
    }

    public void setBackPwOfYear(double backPwOfYear) {
        this.backPwOfYear = backPwOfYear;
    }

    public double getLimitPwOfDay() {
        return limitPwOfDay;
    }

    public void setLimitPwOfDay(double limitPwOfDay) {
        this.limitPwOfDay = limitPwOfDay;
    }

    public double getLimitPwOfMonth() {
        return limitPwOfMonth;
    }

    public void setLimitPwOfMonth(double limitPwOfMonth) {
        this.limitPwOfMonth = limitPwOfMonth;
    }

    public double getLimitPwOfYear() {
        return limitPwOfYear;
    }

    public void setLimitPwOfYear(double limitPwOfYear) {
        this.limitPwOfYear = limitPwOfYear;
    }

    public double getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(double limitRate) {
        this.limitRate = limitRate;
    }

    public double getErrorPwOfDay() {
        return errorPwOfDay;
    }

    public void setErrorPwOfDay(double errorPwOfDay) {
        this.errorPwOfDay = errorPwOfDay;
    }

    public double getErrorPwOfMonth() {
        return errorPwOfMonth;
    }

    public void setErrorPwOfMonth(double errorPwOfMonth) {
        this.errorPwOfMonth = errorPwOfMonth;
    }

    public double getErrorPwOfYear() {
        return errorPwOfYear;
    }

    public void setErrorPwOfYear(double errorPwOfYear) {
        this.errorPwOfYear = errorPwOfYear;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    public double getTheoryPower() {
        return theoryPower;
    }

    public void setTheoryPower(double theoryPower) {
        this.theoryPower = theoryPower;
    }


}
