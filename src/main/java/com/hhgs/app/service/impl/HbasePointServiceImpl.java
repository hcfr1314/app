package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.HbasePointMapper;
import com.hhgs.app.model.DO.hbase.HbasePoint;
import com.hhgs.app.service.HbasePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HbasePointServiceImpl implements HbasePointService {

    @Autowired
    private HbasePointMapper hbasePointMapper;


    @Override
    public Map<Integer, Map<Integer, List<HbasePoint>>> getIdByPlantCodeAndIndexId() {
        List<HbasePoint> hbasePoints = hbasePointMapper.queryAllHbasePoint();
        return hbasePoints.stream().collect(Collectors.groupingBy(HbasePoint::getPlantCode, Collectors.groupingBy(HbasePoint::getIndexId)));
    }
}
