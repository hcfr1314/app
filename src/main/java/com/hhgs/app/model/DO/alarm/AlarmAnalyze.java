package com.hhgs.app.model.DO.alarm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 告警分析实体类
 */
public class AlarmAnalyze {
    /**
     * 主键id
     */
    private String id;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * 场站类型(0.风电 1.水电 2.光伏)
     */
    private int plantType;

    /**
     * 统计时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date statisticsDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 当天一级告警数目
     */
    private int NoOneNum;

    /**
     * 当天二级告警数目
     */
    private int secondaryNum;

    /**
     * 当天三级告警数目
     */
    private int thirdNum;

    /**
     * 当天四级告警数目
     */
    private int fourthNum;

    /**
     * 场站告警总数目
     */
    private int totalNum;

    /**
     * 事故告警数量
     */
    private int accidentNum;

    /**
     * 越复限告警数量
     */
    private int moreLineNum;

    /**
     * 故障告警数量
     */
    private int faultNum;

    public int getAccidentNum() {
        return accidentNum;
    }

    public void setAccidentNum(int accidentNum) {
        this.accidentNum = accidentNum;
    }

    public int getMoreLineNum() {
        return moreLineNum;
    }

    public void setMoreLineNum(int moreLineNum) {
        this.moreLineNum = moreLineNum;
    }

    public int getFaultNum() {
        return faultNum;
    }

    public void setFaultNum(int faultNum) {
        this.faultNum = faultNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPlantType() {
        return plantType;
    }

    public void setPlantType(int plantType) {
        this.plantType = plantType;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNoOneNum() {
        return NoOneNum;
    }

    public void setNoOneNum(int noOneNum) {
        NoOneNum = noOneNum;
    }

    public int getSecondaryNum() {
        return secondaryNum;
    }

    public void setSecondaryNum(int secondaryNum) {
        this.secondaryNum = secondaryNum;
    }

    public int getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(int thirdNum) {
        this.thirdNum = thirdNum;
    }

    public int getFourthNum() {
        return fourthNum;
    }

    public void setFourthNum(int fourthNum) {
        this.fourthNum = fourthNum;
    }

    @Override
    public String toString() {
        return "AlarmAnalyze{" +
                "id='" + id + '\'' +
                ", plantName='" + plantName + '\'' +
                ", plantType=" + plantType +
                ", statisticsDate=" + statisticsDate +
                ", createDate=" + createDate +
                ", NoOneNum=" + NoOneNum +
                ", secondaryNum=" + secondaryNum +
                ", thirdNum=" + thirdNum +
                ", fourthNum=" + fourthNum +
                ", totalNum=" + totalNum +
                ", accidentNum=" + accidentNum +
                ", moreLineNum=" + moreLineNum +
                ", faultNum=" + faultNum +
                '}';
    }
}
