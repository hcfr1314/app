package com.hhgs.app.model.DO.alarm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 告警分析统计查询条件封装类
 * @author lenovo
 *
 */
public class AnalyzeAndStatistic {
   
	private int plantType;
	
	private String plantName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date endTime;

	public int getPlantType() {
		return plantType;
	}

	public void setPlantType(int plantType) {
		this.plantType = plantType;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
