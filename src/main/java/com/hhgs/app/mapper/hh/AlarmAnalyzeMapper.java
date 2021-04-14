package com.hhgs.app.mapper.hh;

import java.util.List;

import com.hhgs.app.model.DO.alarm.AlarmAnalyze;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("hhTemplate")
@Repository
public interface AlarmAnalyzeMapper {

	List<AlarmAnalyze> queryByConditon(@Param("item") AnalyzeAndStatistic entity, @Param("names") List<String> plantNames);

}
