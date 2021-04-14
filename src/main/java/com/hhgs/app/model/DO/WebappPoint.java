package com.hhgs.app.model.DO;

public class WebappPoint {

    //原始测点id
    private long orgId;

    //测点描述
    private String description;

    //场站编号
    private String plantCode;

    //测点标识
    private int orgType;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    @Override
    public String toString() {
        return "WebappPoint{" +
                "orgId='" + orgId + '\'' +
                ", description='" + description + '\'' +
                ", plantCode='" + plantCode + '\'' +
                ", orgType='" + orgType + '\'' +
                '}';
    }
}
