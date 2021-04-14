package com.hhgs.app.model.DO;

import java.util.Date;

/**
 * 存入的计划发电量
 * @author lenovo
 *
 */
public class PlanPower {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 大场站编码
	 */
	private int plantCode;
	
	/**
	 * 小厂站编码
	 */
	private int stationId;
	
	/**
	 * 月计划发电量
	 */
	private double planPw;
	
	/**
	 * 创建时间(每月的1号准时存入上月的计划发电量)
	 */
	private Date createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(int plantCode) {
		this.plantCode = plantCode;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public double getPlanPw() {
		return planPw;
	}

	public void setPlanPw(double planPw) {
		this.planPw = planPw;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
