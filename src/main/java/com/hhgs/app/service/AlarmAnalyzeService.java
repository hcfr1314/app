package com.hhgs.app.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hhgs.app.model.DO.alarm.AlarmAnalyze;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;

public interface AlarmAnalyzeService {

	Map<String, Object> barByMonth(AnalyzeAndStatistic entity);

	List<AlarmAnalyze> yesterdayData(String plantName) throws ParseException;

	List<AlarmAnalyze> recentWeek(String plantName) throws ParseException;
}
