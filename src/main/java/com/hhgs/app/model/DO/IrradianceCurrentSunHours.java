package com.hhgs.app.model.DO;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 辐照度和日照时数实体类
 */
public class IrradianceCurrentSunHours {

    //辐照度
    private double irradiance;

    //日照小时数
    private double currentSunHours;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public double getIrradiance() {
        return irradiance;
    }

    public void setIrradiance(double irradiance) {
        this.irradiance = irradiance;
    }

    public double getCurrentSunHours() {
        return currentSunHours;
    }

    public void setCurrentSunHours(double currentSunHours) {
        this.currentSunHours = currentSunHours;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
