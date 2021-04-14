package com.hhgs.app.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.model.DO.alarm.AlarmStatistics;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;

public interface StatisticsService {
		PageInfo<AlarmStatistics> getForPage(AnalyzeAndStatistic entity, int pageSize, int pageNumber);
}
