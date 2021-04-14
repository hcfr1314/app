package com.hhgs.app.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import com.hhgs.app.mapper.hh.StatisticsMapper;
import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import com.hhgs.app.model.DO.alarm.AlarmStatistics;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.PlantMappingService;
import com.hhgs.app.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statmapper;

    @Autowired
    private PlantMappingService mappingService;

    @Override
    public PageInfo<AlarmStatistics> getForPage(AnalyzeAndStatistic entity, int pageSize, int pageNumber) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Group> groups = user.getGroups();
        List<Group> collect = groups.stream().filter(ele ->
                ele.getGroupName().equals(entity.getPlantName())
        ).collect(Collectors.toList());

        if (collect.size() != 0) {
            AlarmPlantMapping mapping = mappingService.getByPlantName(entity.getPlantName());
            String statTableName = mapping.getStatisticsTableName();
            PageHelper.startPage(pageNumber, pageSize);
            List<AlarmStatistics> list = statmapper.queryByConditon(entity, statTableName);
            PageInfo<AlarmStatistics> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        //没有权限查询该场站信息

        return null;

    }

}
