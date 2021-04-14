package com.hhgs.app.model.DO.alarm;

import java.util.Date;

/**
 * 统计实体类
 *
 */
public class AlarmStatistics {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 场站名称
	 */
	private String plantName;
	
	/**
	 * 告警名称
	 */
	private String alarmName;
	
	/**
	 * 测点id
	 */
	private String orginalId;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 告警类别
	 */
	private String alarmClass;
	
	/**
	 * 告警级别
	 */
	private String alarmLevel;
	
	/**
	 * 统计的是那一天的告警数据
	 */
	private Date alarmDate;
	
	/**
	 * 数据插入时间
	 */
	private Date createDate;
	
	/**
	 * 某个测点下报警次数
	 */
	private int alarmCount;
	
	

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	

	public String getOrginalId() {
		return orginalId;
	}

	public void setOrginalId(String orginalId) {
		this.orginalId = orginalId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAlarmClass() {
		return alarmClass;
	}

	public void setAlarmClass(String alarmClass) {
		this.alarmClass = alarmClass;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}
	
}
