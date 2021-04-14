package com.hhgs.app.service.impl;

import java.util.List;

import com.hhgs.app.mapper.hh.AlarmHistoryMapper;
import com.hhgs.app.model.DO.alarm.AlarmHistory;
import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import com.hhgs.app.service.AlarmHistoryService;
import com.hhgs.app.service.PlantMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AlarmHistoryServiceImpl implements AlarmHistoryService {

	@Autowired
	private AlarmHistoryMapper histroyMapper;
	
	@Autowired
	private PlantMappingService mappingService;

	@Override
	public PageInfo<AlarmHistory> getPageList(AlarmHistory histroy, int pageSize, int pageNumber) {
		AlarmPlantMapping mapping=mappingService.getByPlantName(histroy.getPlantName());
		String tableName=mapping.getHistoryTableName();
		PageHelper.startPage(pageNumber, pageSize);
		List<AlarmHistory> list=histroyMapper.getList(histroy,tableName);
		PageInfo<AlarmHistory> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<AlarmHistory> getList(AlarmHistory histroy,String tableName) {
		return histroyMapper.getList(histroy,tableName);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateStatus(List<Integer> ids,String plantName) throws Exception {
		AlarmPlantMapping mapping=mappingService.getByPlantName(plantName);
		if(mapping==null) {
			throw new Exception("场站名称不正确-------");
		}

		String tableName=mapping.getHistoryTableName();
		if(StringUtils.isEmpty(tableName)) {
			throw new Exception("场站名称不正确-------");
		}

		if(ids.size()<=1000) {
			return histroyMapper.updateStatus(ids,tableName);
		}

		int updates=0;
		int startIndex;
		int endIndex;
		int count=ids.size()/ 1000;
		count=ids.size()%1000==0?count:count+1;

		for(int i=1;i<=count;i++) {
			startIndex=(i-1)*1000;
		    endIndex=i*1000;
		    if(ids.size()<endIndex) {
		    	endIndex=ids.size();
		    }
			List<Integer> tmpList=ids.subList(startIndex, endIndex);
			updates+=histroyMapper.updateStatus(tmpList,tableName);
		}

		return updates;

	}

	@Override
	public List<AlarmHistory> topTen(String historyTableName) {
		return histroyMapper.topTen(historyTableName);
	}


}
