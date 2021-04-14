package com.hhgs.app.mapper.hh;

import java.util.List;

import com.hhgs.app.model.DO.alarm.AlarmStatistics;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("hhTemplate")
public interface StatisticsMapper {

	List<AlarmStatistics> queryByConditon(@Param("item") AnalyzeAndStatistic entity, @Param("tableName") String tableName);

}
