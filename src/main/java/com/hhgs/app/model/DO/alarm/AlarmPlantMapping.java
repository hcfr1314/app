package com.hhgs.app.model.DO.alarm;

public class AlarmPlantMapping {

    //序号
    private int id;

    //场站名称
    private String plantName;

    //YX表名称
    private String tableNameYX;
    
   //YC表名称
    private String tableNameYC;
    
    //topic名称
    private String topicName;
    
    //历史表名称
    private String historyTableName;
    
    //场站类型
    private int plantType;
    
    //统计表名称
    private String statisticsTableName;
    

    public String getStatisticsTableName() {
		return statisticsTableName;
	}

	public void setStatisticsTableName(String statisticsTableName) {
		this.statisticsTableName = statisticsTableName;
	}

	public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getHistoryTableName() {
		return historyTableName;
	}

	public void setHistoryTableName(String historyTableName) {
		this.historyTableName = historyTableName;
	}

	public int getPlantType() {
		return plantType;
	}

	public void setPlantType(int plantType) {
		this.plantType = plantType;
	}

	public String getTableNameYX() {
		return tableNameYX;
	}

	public void setTableNameYX(String tableNameYX) {
		this.tableNameYX = tableNameYX;
	}

	public String getTableNameYC() {
		return tableNameYC;
	}

	public void setTableNameYC(String tableNameYC) {
		this.tableNameYC = tableNameYC;
	}
    
}
