package com.hhgs.app.model.DO;

/**
 * 场站基本信息
 */
public class PlantInfo {

    /**
     * 场站编码
     */
    private int plantCode;

    /**
     * 场站类型(1,光伏 2，风电)
     */
    private String plantType;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * 装机容量
     */
    private double capacity;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    @Override
    public String toString() {
        return "PlantInfo{" +
                "plantCode=" + plantCode +
                ", plantType='" + plantType + '\'' +
                ", plantName='" + plantName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
