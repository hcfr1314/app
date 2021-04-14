package com.hhgs.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hhgs.app.mapper.hh.AlarmAnalyzeMapper;
import com.hhgs.app.model.DO.alarm.AlarmAnalyze;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.AlarmAnalyzeService;
import com.hhgs.app.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AlarmAnalyzeServiceImpl implements AlarmAnalyzeService {

	@Autowired
	private AlarmAnalyzeMapper analyzeMapper;

	private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Map<String, Object> barByMonth(AnalyzeAndStatistic entity) {
		//首先当前用户拥有查询哪些场站的权限
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Group> groups=user.getGroups();
        if(groups.size()>0) {
			List<String> plantNames = new ArrayList<>();
			//获取所有场站的名字
			groups.forEach(ele -> {
                 plantNames.add(ele.getGroupName());
			});

			if(plantNames.size()>0){
				// 构造查询条件
				LocalDate now = LocalDate.now();
				LocalDate firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());
				entity.setStartTime(Date.from(firstDayOfYear.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				entity.setEndTime(new Date());

				// 查询
				List<AlarmAnalyze> analyzeList = analyzeMapper.queryByConditon(entity,plantNames);

				if (analyzeList != null && !analyzeList.isEmpty()) {
					Map<Integer, List<AlarmAnalyze>> tmp = new HashMap<>(now.getMonthValue());

					Map<String,Object> result=new HashMap<>();
					// 按照月份分组
					analyzeList.forEach(ele -> {
						Date date = ele.getStatisticsDate();
						int month = date.getMonth()+1;
						// 如果不包含，则创建
						if (!tmp.containsKey(month)) {
							List<AlarmAnalyze> list = new ArrayList<>();
							list.add(ele);
							tmp.put(month, list);
						} else {
							tmp.get(month).add(ele);
						}

					});

					Map<Integer,AlarmAnalyze> resultMap=new HashMap<>(tmp.size());

					//求各个等级告警数目
					tmp.forEach((month,list)->{
						AlarmAnalyze ele=new AlarmAnalyze();
						list.forEach(item->{
							ele.setNoOneNum(ele.getNoOneNum()+item.getNoOneNum());
							ele.setSecondaryNum(ele.getSecondaryNum()+item.getSecondaryNum());
							ele.setThirdNum(ele.getThirdNum()+item.getThirdNum());
							ele.setFourthNum(ele.getFourthNum()+item.getFourthNum());
							ele.setTotalNum(ele.getTotalNum()+item.getTotalNum());
						});

						resultMap.put(month,ele);

					});

					//处理空缺数据
					int monthOfYear=now.getMonthValue();
					for(int i=1;i<=monthOfYear;i++) {
						if(resultMap.get(i)==null) {
							resultMap.put(i, new AlarmAnalyze());
						}
					}


					result.put("bar", resultMap);

					AlarmAnalyze total=new AlarmAnalyze();
					//计算总数目
					resultMap.forEach((month,item)->{
						total.setNoOneNum(total.getNoOneNum()+item.getNoOneNum());
						total.setSecondaryNum(total.getSecondaryNum()+item.getSecondaryNum());
						total.setThirdNum(total.getThirdNum()+item.getThirdNum());
						total.setFourthNum(total.getFourthNum()+item.getFourthNum());
						total.setTotalNum(total.getTotalNum()+item.getTotalNum());
					});

					result.put("pie", total);

					return result;
				}
			}
		}

		return null;
	}

	@Override
	public List<AlarmAnalyze> yesterdayData(String plantName) throws ParseException {

		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Group> groups=user.getGroups();

		List<Group> filterList=groups.stream().filter(group->group.getGroupName().equals(plantName)).collect(Collectors.toList());

		if(filterList==null || filterList.size()==0){
			throw new RuntimeException("没有权限查看该厂站数据");
		}

		//构造查询条件
		LocalDate now=LocalDate.now();
		String end=format.format(LocalDate.now())+" 00:00:00";
		String start=format.format(now.plusDays(-1))+" 00:00:00";
		AnalyzeAndStatistic analyzeAndStatistic=new AnalyzeAndStatistic();
		analyzeAndStatistic.setStartTime(dateFormat.parse(start));
		analyzeAndStatistic.setEndTime(dateFormat.parse(end));

		List<String> names=new ArrayList<>();
		names.add(plantName);

		List<AlarmAnalyze> alarmAnalyzes = analyzeMapper.queryByConditon(analyzeAndStatistic, names);
		return alarmAnalyzes;
	}

	@Override
	public List<AlarmAnalyze> recentWeek(String plantName) throws ParseException {
		String[] arr=DateUtil.getRecentWeekStartAndEnd();
		//构造查询条件
		AnalyzeAndStatistic analyzeAndStatistic=new AnalyzeAndStatistic();
		analyzeAndStatistic.setStartTime(dateFormat.parse(arr[0]));
		analyzeAndStatistic.setEndTime(dateFormat.parse(arr[1]));

		List<String> names=new ArrayList<>();
		names.add(plantName);
		return analyzeMapper.queryByConditon(analyzeAndStatistic, names);
	}

}
