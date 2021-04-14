package com.hhgs.app.model.DO.wind;

import java.util.Date;

/**
 * 电量指标(WEBAPP_WIND_ELECTRICITY)
 */
public class WindPower {

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
     * 发电量日累计
     */
    private double pwOfDay;

    /**
     * 发电量月累计
     */
    private double pwOfMonth;

    /**
     * 发电量年累计
     */
    private double pwOfYear;

    /**
     * 上网电量日累计
     */
    private double pwNetDay;

    /**
     * 上网电量月累计
     */
    private double pwNetMonth;

    /**
     * 上网电量年累计
     */
    private double pwNetYear;

    /**
     * 返送电量日累计
     */
    private double pwReverseDay;

    /**
     * 返送电量月累计
     */
    private double pwReverseMonth;

    /**
     * 返送电量年累计
     */
    private double pwReverseYear;

    /**
     * 电网限电日累计
     */
    private double limitPwDay;

    /**
     * 电网限电月累计
     */
    private double limitPwMonth;

    /**
     * 电网限电年累计
     */
    private double limitPwYear;

    /**
     * 电网限电弃风电率
     */
    private double limitRate;

    /**
     * 故障弃风电量日累计
     */
    private double breakPwDay;

    /**
     * 故障弃风电量月累计
     */
    private double breakPwMonth;

    /**
     * 故障弃风电量年累计
     */
    private double breakPwYear;

    /**
     * 故障弃风电率
     */
    private double breakRate;

    /**
     * 理论发电量
     */
    private double theoryPw;

    /**
     * 日利用小时数
     */
    private double dayUseHour;

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

    public double getPwOfDay() {
        return pwOfDay;
    }

    public void setPwOfDay(double pwOfDay) {
        this.pwOfDay = pwOfDay;
    }

    public double getPwOfMonth() {
        return pwOfMonth;
    }

    public void setPwOfMonth(double pwOfMonth) {
        this.pwOfMonth = pwOfMonth;
    }

    public double getPwOfYear() {
        return pwOfYear;
    }

    public void setPwOfYear(double pwOfYear) {
        this.pwOfYear = pwOfYear;
    }

    public double getPwNetDay() {
        return pwNetDay;
    }

    public void setPwNetDay(double pwNetDay) {
        this.pwNetDay = pwNetDay;
    }

    public double getPwNetMonth() {
        return pwNetMonth;
    }

    public void setPwNetMonth(double pwNetMonth) {
        this.pwNetMonth = pwNetMonth;
    }

    public double getPwNetYear() {
        return pwNetYear;
    }

    public void setPwNetYear(double pwNetYear) {
        this.pwNetYear = pwNetYear;
    }

    public double getPwReverseDay() {
        return pwReverseDay;
    }

    public void setPwReverseDay(double pwReverseDay) {
        this.pwReverseDay = pwReverseDay;
    }

    public double getPwReverseMonth() {
        return pwReverseMonth;
    }

    public void setPwReverseMonth(double pwReverseMonth) {
        this.pwReverseMonth = pwReverseMonth;
    }

    public double getPwReverseYear() {
        return pwReverseYear;
    }

    public void setPwReverseYear(double pwReverseYear) {
        this.pwReverseYear = pwReverseYear;
    }

    public double getLimitPwDay() {
        return limitPwDay;
    }

    public void setLimitPwDay(double limitPwDay) {
        this.limitPwDay = limitPwDay;
    }

    public double getLimitPwMonth() {
        return limitPwMonth;
    }

    public void setLimitPwMonth(double limitPwMonth) {
        this.limitPwMonth = limitPwMonth;
    }

    public double getLimitPwYear() {
        return limitPwYear;
    }

    public void setLimitPwYear(double limitPwYear) {
        this.limitPwYear = limitPwYear;
    }

    public double getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(double limitRate) {
        this.limitRate = limitRate;
    }

    public double getBreakPwDay() {
        return breakPwDay;
    }

    public void setBreakPwDay(double breakPwDay) {
        this.breakPwDay = breakPwDay;
    }

    public double getBreakPwMonth() {
        return breakPwMonth;
    }

    public void setBreakPwMonth(double breakPwMonth) {
        this.breakPwMonth = breakPwMonth;
    }

    public double getBreakPwYear() {
        return breakPwYear;
    }

    public void setBreakPwYear(double breakPwYear) {
        this.breakPwYear = breakPwYear;
    }

    public double getBreakRate() {
        return breakRate;
    }

    public void setBreakRate(double breakRate) {
        this.breakRate = breakRate;
    }

    public double getTheoryPw() {
        return theoryPw;
    }

    public void setTheoryPw(double theoryPw) {
        this.theoryPw = theoryPw;
    }

    public double getDayUseHour() {
        return dayUseHour;
    }

    public void setDayUseHour(double dayUseHour) {
        this.dayUseHour = dayUseHour;
    }
}
