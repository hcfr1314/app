package com.hhgs.app.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.model.DO.alarm.AlarmHistory;

public interface AlarmHistoryService {
	
   PageInfo<AlarmHistory> getPageList(AlarmHistory histroy, int pageSize, int pageNumber);
   
   List<AlarmHistory> getList(AlarmHistory histroy, String tableName);

   int updateStatus(List<Integer> ids,String plantName) throws Exception;

   List<AlarmHistory> topTen(String historyTableName);
}
