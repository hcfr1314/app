package com.hhgs.app.mapper.hh;

import java.util.List;

import com.hhgs.app.model.DO.alarm.AlarmHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("hhTemplate")
@Repository
public interface AlarmHistoryMapper {

	List<AlarmHistory> getList(@Param("histroy") AlarmHistory histroy, @Param("tableName") String tableName);


	int updateStatus(@Param("ids") List<Integer> ids,@Param("tableName") String tableName);

	List<AlarmHistory> topTen(@Param("tableName") String historyTableName);

}
