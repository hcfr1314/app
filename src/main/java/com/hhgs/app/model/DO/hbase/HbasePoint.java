package com.hhgs.app.model.DO.hbase;

/**
 * 原始测点对象
 */
public class HbasePoint {

    /**
     * 主键
     */
    private long id;

    /**
     * 场站编码
     */
    private int plantCode;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * 描述
     */
    private String description;

    /**
     * 倍率
     */
    private double timesRate;

    /**
     * 对应指标id
     */
    private int indexId;

    public HbasePoint() {
    }

    public HbasePoint(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTimesRate() {
        return timesRate;
    }

    public void setTimesRate(double timesRate) {
        this.timesRate = timesRate;
    }

    public int getIndexId() {
        return indexId;
    }

    public void setIndexId(int indexId) {
        this.indexId = indexId;
    }
}
