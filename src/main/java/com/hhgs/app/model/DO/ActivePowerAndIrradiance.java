package com.hhgs.app.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 有功功率和辐照度业务实体类
 */
public class ActivePowerAndIrradiance {

    //有功功率
    private double activePower;

    //辐照度
    private double irradiance;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getIrradiance() {
        return irradiance;
    }

    public void setIrradiance(double irradiance) {
        this.irradiance = irradiance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
