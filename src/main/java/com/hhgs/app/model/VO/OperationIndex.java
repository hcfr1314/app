package com.hhgs.app.model.VO;

public class OperationIndex {

    //装机容量
    private double installedCapacity;

    //有功功率
    private double activePower;

    //平均气温
    private double averageTemperature;

    //平均风速
    private double avgWindSpeed;

    //日发电量
    private double currentCeneration;

    //月发电量
    private double monthAccCeneration;

    //年发电量
    private double yearAccCeneration;

    //所有场站总装机容量
    private double allInstalledCapacity;

    //所有场站总有功功率
    private double allActivePower;

    //所有场站平均温度
    private double allAverageTemperature;

    //所有场站平均风速
    private double allAvgWindSpeed;

    //所有场站总日发电量
    private double allCurrentCeneration;

    //所有场站总月发电量
    private double allMonthAccCeneration;

    //所有场站总年发电量
    private double allYearAccCeneration;

    //场站编号
    private int plantCode;

    //全场站有功功率
    private double allPw;

    //当前场站有功功率
    private double singlePw;

    public double getAllPw() {
        return allPw;
    }

    public void setAllPw(double allPw) {
        this.allPw = allPw;
    }

    public double getSinglePw() {
        return singlePw;
    }

    public void setSinglePw(double singlePw) {
        this.singlePw = singlePw;
    }

    public double getInstalledCapacity() {
        return installedCapacity;
    }

    public void setInstalledCapacity(double installedCapacity) {
        this.installedCapacity = installedCapacity;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public double getCurrentCeneration() {
        return currentCeneration;
    }

    public void setCurrentCeneration(double currentCeneration) {
        this.currentCeneration = currentCeneration;
    }

    public double getMonthAccCeneration() {
        return monthAccCeneration;
    }

    public void setMonthAccCeneration(double monthAccCeneration) {
        this.monthAccCeneration = monthAccCeneration;
    }

    public double getYearAccCeneration() {
        return yearAccCeneration;
    }

    public void setYearAccCeneration(double yearAccCeneration) {
        this.yearAccCeneration = yearAccCeneration;
    }

    public double getAllInstalledCapacity() {
        return allInstalledCapacity;
    }

    public void setAllInstalledCapacity(double allInstalledCapacity) {
        this.allInstalledCapacity = allInstalledCapacity;
    }

    public double getAllActivePower() {
        return allActivePower;
    }

    public void setAllActivePower(double allActivePower) {
        this.allActivePower = allActivePower;
    }

    public double getAllAverageTemperature() {
        return allAverageTemperature;
    }

    public void setAllAverageTemperature(double allAverageTemperature) {
        this.allAverageTemperature = allAverageTemperature;
    }

    public double getAllAvgWindSpeed() {
        return allAvgWindSpeed;
    }

    public void setAllAvgWindSpeed(double allAvgWindSpeed) {
        this.allAvgWindSpeed = allAvgWindSpeed;
    }

    public double getAllCurrentCeneration() {
        return allCurrentCeneration;
    }

    public void setAllCurrentCeneration(double allCurrentCeneration) {
        this.allCurrentCeneration = allCurrentCeneration;
    }

    public double getAllMonthAccCeneration() {
        return allMonthAccCeneration;
    }

    public void setAllMonthAccCeneration(double allMonthAccCeneration) {
        this.allMonthAccCeneration = allMonthAccCeneration;
    }

    public double getAllYearAccCeneration() {
        return allYearAccCeneration;
    }

    public void setAllYearAccCeneration(double allYearAccCeneration) {
        this.allYearAccCeneration = allYearAccCeneration;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

}
