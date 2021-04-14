package com.hhgs.app.model.DO;

import java.util.Date;

public class StandardValue {

    /**
     * 主键
     */
    private int id;

    /**
     * 综合厂用电率参考值
     */
    private double comsumeEleValue;

    /**
     * 更新值
     */
    private Date updateTime;

    /**
     * 光伏场站可利用率参考值
     */
    private double availablityValue;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 修改人用户名
     */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getComsumeEleValue() {
        return comsumeEleValue;
    }

    public void setComsumeEleValue(double comsumeEleValue) {
        this.comsumeEleValue = comsumeEleValue;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public double getAvailablityValue() {
        return availablityValue;
    }

    public void setAvailablityValue(double availablityValue) {
        this.availablityValue = availablityValue;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }
}
