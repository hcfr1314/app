package com.hhgs.app.model.VO;

public class IndexPlanRate {

    /**
     * 日发电量
     */
    private double powerOfDay;

    /**
     * 月累计发电量
     */
    private double powerOfMonth;

    /**
     * 环比
     */
    private String yOM;

    /**
     * 同比
     */
    private String mOM;

    /**
     * 上月发电量完成率
     */
    private String lastRate;

    public String getLastRate() {
        return lastRate;
    }

    public void setLastRate(String lastRate) {
        this.lastRate = lastRate;
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

    public String getyOM() {
        return yOM;
    }

    public void setyOM(String yOM) {
        this.yOM = yOM;
    }

    public String getmOM() {
        return mOM;
    }

    public void setmOM(String mOM) {
        this.mOM = mOM;
    }
}
