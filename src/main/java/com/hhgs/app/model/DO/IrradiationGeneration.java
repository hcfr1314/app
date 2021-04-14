package com.hhgs.app.model.DO;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 辐照量和发电量实体类
 */
public class IrradiationGeneration {


    //当日发电量
    private double currentGeneration;

    //当日辐照量
    private double currentIrradiation;

    //日照小时数
    private double currentSunHours;


    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public double getCurrentSunHours() {
        return currentSunHours;
    }

    public void setCurrentSunHours(double currentSunHours) {
        this.currentSunHours = currentSunHours;
    }

    public double getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(double currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public double getCurrentIrradiation() {
        return currentIrradiation;
    }

    public void setCurrentIrradiation(double currentIrradiation) {
        this.currentIrradiation = currentIrradiation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
